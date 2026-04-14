package sakayomi.core.migration.migrations

import dev.sakayori.domain.base.BasePreferences
import sakayomi.core.common.FeatureFlags
import sakayomi.core.migration.Migration
import sakayomi.core.migration.MigrationContext
import kotlin.uuid.ExperimentalUuidApi

class InstallationIdMigration : Migration {
    override val version: Float = Migration.ALWAYS

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun invoke(migrationContext: MigrationContext): Boolean {
        val installationId = migrationContext.get<BasePreferences>()?.installationId ?: return false
        if (!installationId.isSet()) installationId.set(FeatureFlags.newInstallationId())
        return true
    }
}
