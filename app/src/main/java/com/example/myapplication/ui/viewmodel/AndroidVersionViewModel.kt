import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.AndroidVersionRepository
import com.example.myapplication.ui.model.ItemUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.random.Random

class AndroidVersionViewModel : ViewModel() {
    private val androidVersionRepository: AndroidVersionRepository by lazy { AndroidVersionRepository() }

    val androidVersionList: StateFlow<List<ItemUi>> =
        androidVersionRepository.selectAllAndroidVersion()
            .map { androidObjectEntities: List<MyAndroidModelData> ->
                androidObjectEntities
                    .groupBy { androidModelData -> androidModelData.versionName }
                    .flatMap { (versionName, itemsOfGroup) ->
                        buildList {
                            add(ItemUi.Header(title = versionName))
                            addAll(itemsOfGroup.map { each ->
                                ItemUi.Item(
                                    each.id,
                                    each.versionName,
                                    each.versionNumber,
                                    each.versionUrl,
                                    each.versionYear,
                                )
                            })
                            add(ItemUi.Footer(numberOfElement = itemsOfGroup.size.toString()))
                        }
                    }
            }
            .stateIn(
                scope = viewModelScope,
                initialValue = emptyList(),
                started = SharingStarted.Lazily
            )


    fun insertAndroidVersion() {
        viewModelScope.launch(Dispatchers.IO) {
            val random = Random.nextInt(0, androidVersions.count())
            androidVersionRepository.insertAndroidVersion(
                androidVersions[random]
            )
        }
    }

    fun deleteAllAndroidVersion() {
        viewModelScope.launch(Dispatchers.IO) {
            androidVersionRepository.deleteAllAndroidVersion()
        }
    }


    val androidVersions = listOf(
        MyAndroidModelData(
            id = 0,
            versionName = "Android Alpha",
            versionNumber = "1.0",
            versionUrl = "https://picsum.photos/seed/alpha/200",
            versionYear = "2008"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Petit Four",
            versionNumber = "1.1",
            versionUrl = "https://picsum.photos/seed/petitfour/200",
            versionYear = "2009"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Cupcake",
            versionNumber = "1.5",
            versionUrl = "https://picsum.photos/seed/cupcake/200",
            versionYear = "2009"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Donut",
            versionNumber = "1.6",
            versionUrl = "https://picsum.photos/seed/donut/200",
            versionYear = "2009"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Eclair",
            versionNumber = "2.0",
            versionUrl = "https://picsum.photos/seed/eclair20/200",
            versionYear = "2009"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Eclair",
            versionNumber = "2.1",
            versionUrl = "https://picsum.photos/seed/eclair21/200",
            versionYear = "2010"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Froyo",
            versionNumber = "2.2",
            versionUrl = "https://picsum.photos/seed/froyo/200",
            versionYear = "2010"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Gingerbread",
            versionNumber = "2.3",
            versionUrl = "https://picsum.photos/seed/gingerbread/200",
            versionYear = "2010"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Honeycomb",
            versionNumber = "3.0",
            versionUrl = "https://picsum.photos/seed/honeycomb30/200",
            versionYear = "2011"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Honeycomb",
            versionNumber = "3.1",
            versionUrl = "https://picsum.photos/seed/honeycomb31/200",
            versionYear = "2011"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Ice Cream Sandwich",
            versionNumber = "4.0",
            versionUrl = "https://picsum.photos/seed/ics/200",
            versionYear = "2011"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Jelly Bean",
            versionNumber = "4.1",
            versionUrl = "https://picsum.photos/seed/jellybean41/200",
            versionYear = "2012"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Jelly Bean",
            versionNumber = "4.2",
            versionUrl = "https://picsum.photos/seed/jellybean42/200",
            versionYear = "2012"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Jelly Bean",
            versionNumber = "4.3",
            versionUrl = "https://picsum.photos/seed/jellybean43/200",
            versionYear = "2013"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "KitKat",
            versionNumber = "4.4",
            versionUrl = "https://picsum.photos/seed/kitkat/200",
            versionYear = "2013"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Lollipop",
            versionNumber = "5.0",
            versionUrl = "https://picsum.photos/seed/lollipop50/200",
            versionYear = "2014"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Lollipop",
            versionNumber = "5.1",
            versionUrl = "https://picsum.photos/seed/lollipop51/200",
            versionYear = "2015"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Marshmallow",
            versionNumber = "6.0",
            versionUrl = "https://picsum.photos/seed/marshmallow/200",
            versionYear = "2015"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Nougat",
            versionNumber = "7.0",
            versionUrl = "https://picsum.photos/seed/nougat70/200",
            versionYear = "2016"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Nougat",
            versionNumber = "7.1",
            versionUrl = "https://picsum.photos/seed/nougat71/200",
            versionYear = "2016"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Oreo",
            versionNumber = "8.0",
            versionUrl = "https://picsum.photos/seed/oreo80/200",
            versionYear = "2017"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Oreo",
            versionNumber = "8.1",
            versionUrl = "https://picsum.photos/seed/oreo81/200",
            versionYear = "2017"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Pie",
            versionNumber = "9.0",
            versionUrl = "https://picsum.photos/seed/pie/200",
            versionYear = "2018"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Android 10 (Quince Tart)",
            versionNumber = "10.0",
            versionUrl = "https://picsum.photos/seed/android10/200",
            versionYear = "2019"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Android 11 (Red Velvet Cake)",
            versionNumber = "11.0",
            versionUrl = "https://picsum.photos/seed/android11/200",
            versionYear = "2020"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Android 12 (Snow Cone)",
            versionNumber = "12.0",
            versionUrl = "https://picsum.photos/seed/android12/200",
            versionYear = "2021"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Android 12L",
            versionNumber = "12.1",
            versionUrl = "https://picsum.photos/seed/android12l/200",
            versionYear = "2022"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Android 13 (Tiramisu)",
            versionNumber = "13.0",
            versionUrl = "https://picsum.photos/seed/android13/200",
            versionYear = "2022"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Android 14 (Upside Down Cake)",
            versionNumber = "14.0",
            versionUrl = "https://picsum.photos/seed/android14/200",
            versionYear = "2023"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Android 15 (Vanilla Ice Cream)",
            versionNumber = "15.0",
            versionUrl = "https://picsum.photos/seed/android15/200",
            versionYear = "2024"
        ),
        MyAndroidModelData(
            id = 0,
            versionName = "Android 16 (Baklava)",
            versionNumber = "16.0",
            versionUrl = "https://picsum.photos/seed/android16/200",
            versionYear = "2025"
        )
    )

}