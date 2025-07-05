/*
 * [경로] kr.arkite.core.stat.StatType
 *
 * [역할] Arkite-Core에서 관리하는 모든 기본 스탯 Enum.
 * [설계 근거] 스탯 종류를 Enum으로 관리, 확장/유지보수 편의성.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core.stat;

public enum StatType {
    VIT, // 체력
    STR, // 힘
    DEX, // 민첩
    INT, // 지능
    LUK  // 운
    // 필요시 스탯 자유롭게 추가/확장
}
