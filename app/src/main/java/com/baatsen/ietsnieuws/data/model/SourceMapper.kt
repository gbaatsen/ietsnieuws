package com.baatsen.ietsnieuws.data.model

import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.domain.model.Source

class SourceMapper {
    fun transform(sourcesJson: SourcesJson): List<Source> {

        return sourcesJson.sources.map {
            Source(
                id = it.id,
                name = it.name,
                description = it.description,
                category = it.category,
                url = it.url,
                language = it.language,
                country = it.country,
                flag = getFlag(it.country)
            )
        }
    }

    private fun getFlag(country: String?): Int {
        return when (country?.toLowerCase()) {
            "us" -> R.drawable.flag_us
            "nl" -> R.drawable.flag_nl
            "au" -> R.drawable.flag_au
            "it" -> R.drawable.flag_it
            "gb" -> R.drawable.flag_gb
            "de" -> R.drawable.flag_de
            "br" -> R.drawable.flag_br
            "es" -> R.drawable.flag_es
            "fr" -> R.drawable.flag_fr
            "ru" -> R.drawable.flag_ru
            "be" -> R.drawable.flag_be
            "ca" -> R.drawable.flag_ca
            "in" -> R.drawable.flag_in
            "no" -> R.drawable.flag_no
            "pk" -> R.drawable.flag_pk
            "za" -> R.drawable.flag_za
            "sa" -> R.drawable.flag_sa
            "ar" -> R.drawable.flag_ar
            "is" -> R.drawable.flag_is
            "se" -> R.drawable.flag_se
            "ie" -> R.drawable.flag_ie
            "zh" -> R.drawable.flag_zh
            else -> R.drawable.flag_missing
        }
    }


}