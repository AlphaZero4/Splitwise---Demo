package org.vip.splitwise.strategies;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("RatioShareStrategy")
public class RatioShareStrategy implements ShareStrategy {
    @Override
    public Map<String, Double> getAllUsersShare(List<String> allUserIds, List<Double> paidAmounts, List<Integer> shareUnits) {
        Map<String, Double> usersShare = new HashMap<>();
        List<String> orderedUserIds = allUserIds.stream().sorted().toList();
        Double totalAmount = paidAmounts.stream().mapToDouble(Double::valueOf).reduce(0, Double::sum);
        for (int i = 0; i < orderedUserIds.size(); i++) {
            Double curUserShare = (totalAmount / 10) * shareUnits.get(i);
            usersShare.put(orderedUserIds.get(i), curUserShare);
        }
        return usersShare;
    }
}
