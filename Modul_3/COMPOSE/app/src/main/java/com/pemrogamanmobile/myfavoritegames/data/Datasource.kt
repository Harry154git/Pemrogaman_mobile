package com.pemrogamanmobile.myfavoritegames.data
import com.pemrogamanmobile.myfavoritegames.R
import com.pemrogamanmobile.myfavoritegames.model.Affirmation

class Datasource {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.detail1, R.drawable.image1, R.string.link1, R.string.year1, R.string.title1),
            Affirmation(R.string.detail2, R.drawable.image2, R.string.link2, R.string.year2, R.string.title2),
            Affirmation(R.string.detail3, R.drawable.image3, R.string.link3, R.string.year3, R.string.title3),
            Affirmation(R.string.detail4, R.drawable.image4, R.string.link4, R.string.year4, R.string.title4),
            Affirmation(R.string.detail5, R.drawable.image5, R.string.link5, R.string.year5, R.string.title5),
            Affirmation(R.string.detail6, R.drawable.image6, R.string.link6, R.string.year6, R.string.title6),
            Affirmation(R.string.detail7, R.drawable.image7, R.string.link7, R.string.year7, R.string.title7),
            Affirmation(R.string.detail8, R.drawable.image8, R.string.link8, R.string.year8, R.string.title8),
            Affirmation(R.string.detail9, R.drawable.image9, R.string.link9, R.string.year9, R.string.title9),
            Affirmation(R.string.detail10, R.drawable.image10, R.string.link10, R.string.year10, R.string.title10))
    }
}