/*
 * [경로] kr.arkite.core.stat.PlayerExpData
 *
 * [역할] 플레이어의 경험치, 레벨, 경험치 획득소스 등 저장.
 * [설계 근거] Stat 데이터와 분리, 성장/기록 등 별도 관리.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core.stat;

public class PlayerExpData {
    private int level = 1;
    private double exp = 0.0;
    private ExpSourceType lastSource = ExpSourceType.ETC;

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public double getExp() { return exp; }
    public void setExp(double exp) { this.exp = exp; }

    public ExpSourceType getLastSource() { return lastSource; }
    public void setLastSource(ExpSourceType source) { this.lastSource = source; }
}
