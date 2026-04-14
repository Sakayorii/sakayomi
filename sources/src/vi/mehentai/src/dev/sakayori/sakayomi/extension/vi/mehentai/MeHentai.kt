package dev.sakayori.sakayomi.extension.vi.mehentai

import dev.sakayori.sakayomi.multisrc.manhwaz.ManhwaZ

class MeHentai :
    ManhwaZ(
        "MeHentai",
        "https://mehentai.space",
        "vi",
        mangaDetailsAuthorHeading = "Tác giả",
        mangaDetailsStatusHeading = "Trạng thái",
    ) {
    override val searchPath = "tim-kiem"
}
