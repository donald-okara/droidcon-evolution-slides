package ke.don.resources

import ski.shared.resources.generated.resources.*


object Resources {
    object Strings {
        val APP_NAME = Res.string.app_name
    }
    object Font {
        val MONTSERRAT = Res.font.montserrat_variable
        val ROBOTO_MONO_EXTRA_LIGHT = Res.font.roboto_mono_extra_light
    }

    object Images {

        //Logos
        val DROIDCON_LOGO = Res.drawable.droidcon_logo
        val DROIDCON_LOGO_DARK = Res.drawable.droidcon_logo_dark

        //People
        val IAN = Res.drawable.ian_dooley
        val IVANA = Res.drawable.ivana_cajina
        val RAFAELLA = Res.drawable.rafaella_mendes
        val ANDROID_ROBOT = Res.drawable.android_head_3D

        //Talk assets
        val PLAY_QR = Res.drawable.playstore_droidcon_kotlin
        val REPO_QR = Res.drawable.github_droidcon_ke_kotlin
        val HOME_2026 = Res.drawable.home_screen_2026_w_agenda
        val SESSIONS_2026 = Res.drawable.sessions_2026
        val SETTINGS_GRADLE = Res.drawable.settings_gradle
        val TOP_OF_AGENTS = Res.drawable.top_of_agents
    }

    object Videos {
        /** Path of the bundled sample video, relative to the resources root. Resolve it with `resolveVideoUriForPlayer`. */
        const val SAMPLE_VIDEO_PATH = "files/sample_video.mov"
    }
}