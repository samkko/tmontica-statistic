package com.internship.tmontica_statistic.statistic;

import com.internship.tmontica_statistic.menu.MenuDao;
import com.internship.tmontica_statistic.menu.vo.MenuIdName;
import com.internship.tmontica_statistic.order.OrderWithUserId;
import com.internship.tmontica_statistic.order.OrderDao;
import com.internship.tmontica_statistic.order.OrderDetailWithStatus;
import com.internship.tmontica_statistic.statistic.datatype.AgeGroup;
import com.internship.tmontica_statistic.statistic.vo.SalesWithAgeData;
import com.internship.tmontica_statistic.statistic.vo.SalesWithMenuData;
import com.internship.tmontica_statistic.user.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatisticScheduler {

    private final UserDao userDao;
    private final OrderDao orderDao;
    private final MenuDao menuDao;
    private final StatisticDao statisticDao;
    private static final int SCHEDULING_INTERVAL_MINUTE = 1;

    @Scheduled(cron = "0  0/"+SCHEDULING_INTERVAL_MINUTE+" *  *  * ?")
    public void makeTotalSalesByAgeGroupData(){

        log.info("[scheduler] start makeTotalSalesByAgeGroup scheduler");
        // 현재로부터 스케쥴링 주기 전의 유저별 총 구매액
        Map<String, Integer> totalPriceByUser = orderDao.getOrderByDate(SCHEDULING_INTERVAL_MINUTE).stream().filter(OrderWithUserId::isRealSales)
                .collect(Collectors.groupingBy(OrderWithUserId::getUserId, Collectors.summingInt(OrderWithUserId::getRealPrice)));

        // 연령별 총구매액 맵
        Map<String, Integer> totalSalesByAgeGroup =  userDao.getAllUser().stream()
                .filter(v->totalPriceByUser.keySet().contains(v.getId()))
                .collect(Collectors.toMap(v-> AgeGroup.getAgeGroup(v.getBirthDate()), v->totalPriceByUser.get(v.getId()),
                        Integer::sum));

        for(String ageGroup : totalSalesByAgeGroup.keySet()){
            statisticDao.saveSalesAgeGroupData(new SalesWithAgeData(ageGroup, totalSalesByAgeGroup.get(ageGroup)));
        }
        log.info("[scheduler] end makeTotalSalesByAgeGroup scheduler");
    }

    @Scheduled(cron = "0  0/"+SCHEDULING_INTERVAL_MINUTE+" *  *  * ?")
    public void makeTotalSalesByMenu(){

        log.info("[scheduler] start makeTotalSalesByMenu scheduler");
        // 메뉴 id : 한글이름 Map
        Map<Integer, String> menuIdMap = menuDao.getAllMenuIdAndName().stream()
                .collect(Collectors.toMap(MenuIdName::getId, MenuIdName::getNameKo));
        // 현재로부터 스케쥴링 주기 전의 주문에서 유효한 주문을 filter 후 collect 한 (메뉴 id : 매출액) Map
        Map<Integer, Integer> totalSalesByMenuId = orderDao.getOrderDetailWithStatusByIntervalMinute(SCHEDULING_INTERVAL_MINUTE).stream()
                .filter(OrderDetailWithStatus::isRealSales)
                .collect(Collectors.toMap(OrderDetailWithStatus::getMenuId, OrderDetailWithStatus::getTotalPrice, Integer::sum));

        for(Integer menuId : totalSalesByMenuId.keySet()){
            statisticDao.saveSalesMenuData(new SalesWithMenuData(menuId, totalSalesByMenuId.get(menuId), menuIdMap.get(menuId)));
        }
        log.info("[scheduler] end makeTotalSalesByMenu scheduler");
    }
}
