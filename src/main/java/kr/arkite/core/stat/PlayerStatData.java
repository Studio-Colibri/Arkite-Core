/*
 * [경로] kr.arkite.core.stat.PlayerStatData
 *
 * [역할] 한 플레이어의 스탯 값(StatType별)을 저장/수정/불러오기 위한 자료구조.
 * [설계 근거] StatType-값 쌍으로 관리, 가변/확장성.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core.stat;

import java.util.EnumMap;
import java.util.Map;

public class PlayerStatData {
    private final Map<StatType, Integer> statMap = new EnumMap<>(StatType.class);

    public PlayerStatData() {
        for (StatType type : StatType.values()) {
            statMap.put(type, 5); // 모든 스탯 기본값 5로 시작(Arkite 정책)
        }
    }

    public int get(StatType type) {
        return statMap.getOrDefault(type, 0);
    }

    public void set(StatType type, int value) {
        statMap.put(type, value);
    }

    public void add(StatType type, int delta) {
        statMap.put(type, get(type) + delta);
    }

    public Map<StatType, Integer> getAll() {
        return statMap;
    }
}
