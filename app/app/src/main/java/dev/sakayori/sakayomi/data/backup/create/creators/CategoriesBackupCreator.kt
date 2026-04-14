package dev.sakayori.sakayomi.data.backup.create.creators

import dev.sakayori.sakayomi.data.backup.models.BackupCategory
import dev.sakayori.sakayomi.data.backup.models.backupCategoryMapper
import tachiyomi.domain.category.interactor.GetCategories
import tachiyomi.domain.category.model.Category
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class CategoriesBackupCreator(
    private val getCategories: GetCategories = Injekt.get(),
) {

    suspend operator fun invoke(): List<BackupCategory> {
        return getCategories.await()
            .filterNot(Category::isSystemCategory)
            .map(backupCategoryMapper)
    }
}
