package dev.sakayori.domain

import dev.sakayori.domain.chapter.interactor.GetAvailableScanlators
import dev.sakayori.domain.chapter.interactor.SetReadStatus
import dev.sakayori.domain.chapter.interactor.SyncChaptersWithSource
import dev.sakayori.domain.download.interactor.DeleteDownload
import dev.sakayori.domain.extension.interactor.GetExtensionLanguages
import dev.sakayori.domain.extension.interactor.GetExtensionSources
import dev.sakayori.domain.extension.interactor.GetExtensionsByType
import dev.sakayori.domain.extension.interactor.TrustExtension
import dev.sakayori.domain.manga.interactor.GetExcludedScanlators
import dev.sakayori.domain.manga.interactor.SetExcludedScanlators
import dev.sakayori.domain.manga.interactor.SetMangaViewerFlags
import dev.sakayori.domain.manga.interactor.UpdateManga
import dev.sakayori.domain.source.interactor.GetEnabledSources
import dev.sakayori.domain.source.interactor.GetIncognitoState
import dev.sakayori.domain.source.interactor.GetLanguagesWithSources
import dev.sakayori.domain.source.interactor.GetSourcesWithFavoriteCount
import dev.sakayori.domain.source.interactor.SetMigrateSorting
import dev.sakayori.domain.source.interactor.ToggleIncognito
import dev.sakayori.domain.source.interactor.ToggleLanguage
import dev.sakayori.domain.source.interactor.ToggleSource
import dev.sakayori.domain.source.interactor.ToggleSourcePin
import dev.sakayori.domain.track.interactor.AddTracks
import dev.sakayori.domain.track.interactor.RefreshTracks
import dev.sakayori.domain.track.interactor.SyncChapterProgressWithTrack
import dev.sakayori.domain.track.interactor.TrackChapter
import sakayomi.data.repository.ExtensionRepoRepositoryImpl
import sakayomi.domain.chapter.interactor.FilterChaptersForDownload
import sakayomi.domain.extensionrepo.interactor.CreateExtensionRepo
import sakayomi.domain.extensionrepo.interactor.DeleteExtensionRepo
import sakayomi.domain.extensionrepo.interactor.GetExtensionRepo
import sakayomi.domain.extensionrepo.interactor.GetExtensionRepoCount
import sakayomi.domain.extensionrepo.interactor.ReplaceExtensionRepo
import sakayomi.domain.extensionrepo.interactor.UpdateExtensionRepo
import sakayomi.domain.extensionrepo.repository.ExtensionRepoRepository
import sakayomi.domain.extensionrepo.service.ExtensionRepoService
import sakayomi.domain.migration.usecases.MigrateMangaUseCase
import sakayomi.domain.upcoming.interactor.GetUpcomingManga
import tachiyomi.data.category.CategoryRepositoryImpl
import tachiyomi.data.chapter.ChapterRepositoryImpl
import tachiyomi.data.history.HistoryRepositoryImpl
import tachiyomi.data.manga.MangaRepositoryImpl
import tachiyomi.data.release.ReleaseServiceImpl
import tachiyomi.data.source.SourceRepositoryImpl
import tachiyomi.data.source.StubSourceRepositoryImpl
import tachiyomi.data.track.TrackRepositoryImpl
import tachiyomi.data.updates.UpdatesRepositoryImpl
import tachiyomi.domain.category.interactor.CreateCategoryWithName
import tachiyomi.domain.category.interactor.DeleteCategory
import tachiyomi.domain.category.interactor.GetCategories
import tachiyomi.domain.category.interactor.RenameCategory
import tachiyomi.domain.category.interactor.ReorderCategory
import tachiyomi.domain.category.interactor.ResetCategoryFlags
import tachiyomi.domain.category.interactor.SetDisplayMode
import tachiyomi.domain.category.interactor.SetMangaCategories
import tachiyomi.domain.category.interactor.SetSortModeForCategory
import tachiyomi.domain.category.interactor.UpdateCategory
import tachiyomi.domain.category.repository.CategoryRepository
import tachiyomi.domain.chapter.interactor.GetBookmarkedChaptersByMangaId
import tachiyomi.domain.chapter.interactor.GetChapter
import tachiyomi.domain.chapter.interactor.GetChapterByUrlAndMangaId
import tachiyomi.domain.chapter.interactor.GetChaptersByMangaId
import tachiyomi.domain.chapter.interactor.SetMangaDefaultChapterFlags
import tachiyomi.domain.chapter.interactor.ShouldUpdateDbChapter
import tachiyomi.domain.chapter.interactor.UpdateChapter
import tachiyomi.domain.chapter.repository.ChapterRepository
import tachiyomi.domain.history.interactor.GetHistory
import tachiyomi.domain.history.interactor.GetNextChapters
import tachiyomi.domain.history.interactor.GetTotalReadDuration
import tachiyomi.domain.history.interactor.RemoveHistory
import tachiyomi.domain.history.interactor.UpsertHistory
import tachiyomi.domain.history.repository.HistoryRepository
import tachiyomi.domain.manga.interactor.FetchInterval
import tachiyomi.domain.manga.interactor.GetDuplicateLibraryManga
import tachiyomi.domain.manga.interactor.GetFavorites
import tachiyomi.domain.manga.interactor.GetLibraryManga
import tachiyomi.domain.manga.interactor.GetManga
import tachiyomi.domain.manga.interactor.GetMangaByUrlAndSourceId
import tachiyomi.domain.manga.interactor.GetMangaWithChapters
import tachiyomi.domain.manga.interactor.NetworkToLocalManga
import tachiyomi.domain.manga.interactor.ResetViewerFlags
import tachiyomi.domain.manga.interactor.SetMangaChapterFlags
import tachiyomi.domain.manga.interactor.UpdateMangaNotes
import tachiyomi.domain.manga.repository.MangaRepository
import tachiyomi.domain.release.interactor.GetApplicationRelease
import tachiyomi.domain.release.service.ReleaseService
import tachiyomi.domain.source.interactor.GetRemoteManga
import tachiyomi.domain.source.interactor.GetSourcesWithNonLibraryManga
import tachiyomi.domain.source.repository.SourceRepository
import tachiyomi.domain.source.repository.StubSourceRepository
import tachiyomi.domain.track.interactor.DeleteTrack
import tachiyomi.domain.track.interactor.GetTracks
import tachiyomi.domain.track.interactor.GetTracksPerManga
import tachiyomi.domain.track.interactor.InsertTrack
import tachiyomi.domain.track.repository.TrackRepository
import tachiyomi.domain.updates.interactor.GetUpdates
import tachiyomi.domain.updates.repository.UpdatesRepository
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addFactory
import uy.kohesive.injekt.api.addSingletonFactory
import uy.kohesive.injekt.api.get

class DomainModule : InjektModule {

    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory<CategoryRepository> { CategoryRepositoryImpl(get()) }
        addFactory { GetCategories(get()) }
        addFactory { ResetCategoryFlags(get(), get()) }
        addFactory { SetDisplayMode(get()) }
        addFactory { SetSortModeForCategory(get(), get()) }
        addFactory { CreateCategoryWithName(get(), get()) }
        addFactory { RenameCategory(get()) }
        addFactory { ReorderCategory(get()) }
        addFactory { UpdateCategory(get()) }
        addFactory { DeleteCategory(get(), get(), get()) }

        addSingletonFactory<MangaRepository> { MangaRepositoryImpl(get()) }
        addFactory { GetDuplicateLibraryManga(get()) }
        addFactory { GetFavorites(get()) }
        addFactory { GetLibraryManga(get()) }
        addFactory { GetMangaWithChapters(get(), get()) }
        addFactory { GetMangaByUrlAndSourceId(get()) }
        addFactory { GetManga(get()) }
        addFactory { GetNextChapters(get(), get(), get()) }
        addFactory { GetUpcomingManga(get()) }
        addFactory { ResetViewerFlags(get()) }
        addFactory { SetMangaChapterFlags(get()) }
        addFactory { FetchInterval(get()) }
        addFactory { SetMangaDefaultChapterFlags(get(), get(), get()) }
        addFactory { SetMangaViewerFlags(get()) }
        addFactory { NetworkToLocalManga(get()) }
        addFactory { UpdateManga(get(), get()) }
        addFactory { UpdateMangaNotes(get()) }
        addFactory { SetMangaCategories(get()) }
        addFactory { GetExcludedScanlators(get()) }
        addFactory { SetExcludedScanlators(get()) }
        addFactory {
            MigrateMangaUseCase(
                get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(),
            )
        }

        addSingletonFactory<ReleaseService> { ReleaseServiceImpl(get(), get()) }
        addFactory { GetApplicationRelease(get(), get()) }

        addSingletonFactory<TrackRepository> { TrackRepositoryImpl(get()) }
        addFactory { TrackChapter(get(), get(), get(), get()) }
        addFactory { AddTracks(get(), get(), get(), get()) }
        addFactory { RefreshTracks(get(), get(), get(), get()) }
        addFactory { DeleteTrack(get()) }
        addFactory { GetTracksPerManga(get()) }
        addFactory { GetTracks(get()) }
        addFactory { InsertTrack(get()) }
        addFactory { SyncChapterProgressWithTrack(get(), get(), get()) }

        addSingletonFactory<ChapterRepository> { ChapterRepositoryImpl(get()) }
        addFactory { GetChapter(get()) }
        addFactory { GetChaptersByMangaId(get()) }
        addFactory { GetBookmarkedChaptersByMangaId(get()) }
        addFactory { GetChapterByUrlAndMangaId(get()) }
        addFactory { UpdateChapter(get()) }
        addFactory { SetReadStatus(get(), get(), get(), get()) }
        addFactory { ShouldUpdateDbChapter() }
        addFactory { SyncChaptersWithSource(get(), get(), get(), get(), get(), get(), get(), get(), get()) }
        addFactory { GetAvailableScanlators(get()) }
        addFactory { FilterChaptersForDownload(get(), get(), get()) }

        addSingletonFactory<HistoryRepository> { HistoryRepositoryImpl(get()) }
        addFactory { GetHistory(get()) }
        addFactory { UpsertHistory(get()) }
        addFactory { RemoveHistory(get()) }
        addFactory { GetTotalReadDuration(get()) }

        addFactory { DeleteDownload(get(), get()) }

        addFactory { GetExtensionsByType(get(), get()) }
        addFactory { GetExtensionSources(get()) }
        addFactory { GetExtensionLanguages(get(), get()) }

        addSingletonFactory<UpdatesRepository> { UpdatesRepositoryImpl(get()) }
        addFactory { GetUpdates(get()) }

        addSingletonFactory<SourceRepository> { SourceRepositoryImpl(get(), get()) }
        addSingletonFactory<StubSourceRepository> { StubSourceRepositoryImpl(get()) }
        addFactory { GetEnabledSources(get(), get()) }
        addFactory { GetLanguagesWithSources(get(), get()) }
        addFactory { GetRemoteManga(get()) }
        addFactory { GetSourcesWithFavoriteCount(get(), get()) }
        addFactory { GetSourcesWithNonLibraryManga(get()) }
        addFactory { SetMigrateSorting(get()) }
        addFactory { ToggleLanguage(get()) }
        addFactory { ToggleSource(get()) }
        addFactory { ToggleSourcePin(get()) }
        addFactory { TrustExtension(get(), get()) }

        addSingletonFactory<ExtensionRepoRepository> { ExtensionRepoRepositoryImpl(get()) }
        addFactory { ExtensionRepoService(get(), get()) }
        addFactory { GetExtensionRepo(get()) }
        addFactory { GetExtensionRepoCount(get()) }
        addFactory { CreateExtensionRepo(get(), get()) }
        addFactory { DeleteExtensionRepo(get()) }
        addFactory { ReplaceExtensionRepo(get()) }
        addFactory { UpdateExtensionRepo(get(), get()) }
        addFactory { ToggleIncognito(get()) }
        addFactory { GetIncognitoState(get(), get(), get()) }
    }
}
