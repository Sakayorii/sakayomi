package dev.sakayori.sakayomi.extension.pt.verdinha

import dev.sakayori.sakayomi.multisrc.greenshit.GreenShit

class Verdinha : GreenShit() {
    override val apiUrl = "https://api.verdinha.wtf"
    override val cdnUrl = "https://cdn.verdinha.wtf"
    override val cdnApiUrl = "https://api.verdinha.wtf/cdn"
    override val baseUrl = "https://verdinha.wtf"
    override val lang = "pt-BR"
    override val name = "Verdinha"
    override val scanId = "1"
}
