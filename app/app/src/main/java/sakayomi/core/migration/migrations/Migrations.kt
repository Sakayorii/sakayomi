package sakayomi.core.migration.migrations

import sakayomi.core.migration.Migration

val migrations: List<Migration>
    get() = listOf(
        SetupBackupCreateMigration(),
        SetupLibraryUpdateMigration(),
        TrustExtensionRepositoryMigration(),
        CategoryPreferencesCleanupMigration(),
        InstallationIdMigration(),
    )
