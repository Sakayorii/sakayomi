package sakayomi.core.migration.migrations

import android.app.Application
import dev.sakayori.sakayomi.data.library.LibraryUpdateJob
import sakayomi.core.migration.Migration
import sakayomi.core.migration.MigrationContext

class SetupLibraryUpdateMigration : Migration {
    override val version: Float = Migration.ALWAYS

    override suspend fun invoke(migrationContext: MigrationContext): Boolean {
        val context = migrationContext.get<Application>() ?: return false
        LibraryUpdateJob.setupTask(context)
        return true
    }
}
