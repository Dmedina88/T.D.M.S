package inc.grayherring.com.core.models

data class Achievement(val id: Int, val name: String, val detail: String, val unlocked: Boolean)

//Meconium is the earliest stool of a mammalian infant. Unlike later feces, meconium is composed of materials ingested
// during the time the infant spends in the uterus: intestinal epithelial cells, lanugo, mucus, amniotic fluid, bile, and water.

/*
type - IN APP EVENT (CHECKENING LICINCES, DONATIING) ,
 ENTRY EVENT (X NUMBERS OF ENTRY OIF TYPE)(X TYPES IN A ROW),
 */

sealed class Achievements {
  object ReadLicenses : Achievements()

  object PoopType1_1 : Achievements()
  object PoopType1_100 : Achievements()
  object PoopType1_1000 : Achievements()
  object PoopType2_1 : Achievements()
  object PoopType2_100 : Achievements()
  object PoopType2_1000 : Achievements()
  object PoopType3_1 : Achievements()
  object PoopType4_100 : Achievements()
  object PoopType4_1000 : Achievements()
  object PoopType5_1 : Achievements()
  object PoopType5_100 : Achievements()
  object PoopType5_1000 : Achievements()
  object PoopType6_1 : Achievements()
  object PoopType6_100 : Achievements()
  object PoopType6_1000 : Achievements()
  object PoopType7_1 : Achievements()
  object PoopType7_100 : Achievements()
  object PoopType7_1000 : Achievements()
}