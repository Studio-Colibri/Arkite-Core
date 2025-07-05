/*
 * [경로] kr.arkite.core.stat.ExpManager
 *
 * [역할] 경험치/레벨 업, 소스별 경험치 부여 등 전체 관리.
 * [설계 근거] 중앙 집중적 레벨/경험치 처리.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core.stat;

public class ExpManager {
    public void addExp(PlayerExpData data, double amount, ExpSourceType source) {
        data.setExp(data.getExp() + amount);
        data.setLastSource(source);

        // 레벨업 로직 예시 (간단)
        while (data.getExp() >= requiredExp(data.getLevel())) {
            data.setExp(data.getExp() - requiredExp(data.getLevel()));
            data.setLevel(data.getLevel() + 1);
        }
    }

    public double requiredExp(int level) {
        return 100 + (level - 1) * 20; // 예시: 1렙→2렙 100, 2렙→3렙 120, ...
    }
}
