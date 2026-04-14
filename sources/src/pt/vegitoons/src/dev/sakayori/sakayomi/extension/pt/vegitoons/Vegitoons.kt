package dev.sakayori.sakayomi.extension.pt.vegitoons

import dev.sakayori.sakayomi.multisrc.greenshit.GreenShit

class Vegitoons : GreenShit() {
    override val apiUrl = "https://api.vegitoons.black"
    override val cdnUrl = "https://cdn.verdinha.wtf"
    override val cdnApiUrl = "https://api.vegitoons.black/cdn"
    override val baseUrl = "https://vegitoons.black"
    override val lang = "pt-BR"
    override val name = "Vegitoons"
    override val scanId = "1"
}
