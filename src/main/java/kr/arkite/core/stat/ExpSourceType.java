/*
 * [경로] kr.arkite.core.stat.ExpSourceType
 *
 * [역할] 경험치 획득 소스(몬스터, 채광, 낚시 등) 종류 Enum.
 * [설계 근거] 시스템 내 경험치 분류, 확장 용이.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core.stat;

public enum ExpSourceType {
    MONSTER_KILL,
    BLOCK_BREAK,
    QUEST,
    FISHING,
    ETC
    // 필요시 추가
}
