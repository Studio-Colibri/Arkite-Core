/*
 * [경로] kr.arkite.core.data.PlayerSession
 *
 * [역할] 한 플레이어의 메모리 상 세션 데이터(스탯, 경험치, 설정 등) 관리.
 * [설계 근거] 플러그인 세션 중 지속적으로 접근/변경되는 데이터 보관/저장.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core.data;

import kr.arkite.core.stat.PlayerExpData;
import kr.arkite.core.stat.PlayerStatData;

import java.util.UUID;

public class PlayerSession {

    private final UUID uuid;
    private String name;
    // 스탯, 경험치 등 필요 필드
    private int level;
    private double exp;

    private PlayerStatData statData = new PlayerStatData();
    private PlayerExpData expData = new PlayerExpData();

    public PlayerSession(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.level = 1;
        this.exp = 0.0;
    }

    public UUID getUuid() { return uuid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public double getExp() { return exp; }
    public void setExp(double exp) { this.exp = exp; }

    // 기타: 커스텀 스탯, 설정 등 필요한 만큼 확장

    public PlayerStatData getStatData() { return statData; }
    public PlayerExpData getExpData() { return expData; }
}
