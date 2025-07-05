/*
 * [경로] kr.arkite.core.command.CommandRegistry
 *
 * [역할] Arkite-Core의 명령어 등록 담당. 모든 커맨드의 등록 엔트리포인트 역할.
 * [설계 근거] 각 명령어별 CommandExecutor 객체를 플러그인 인스턴스와 함께 등록.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core.command;

import kr.arkite.core.ArkiteCore;

public class CommandRegistry {
    public static void register(ArkiteCore plugin) {
        plugin.getCommand("core").setExecutor(new CoreCommand(plugin));
        // 추후 다른 명령어도 동일하게 등록
    }
}
