# Android Room
<img width="250px" height="500px" src="/room/sample/sample.gif" />
<br/>
<br/>



## 1. Room

> Room 은 SQLite 의 추상 레이어를 제공하여 SQLite 의 객체를 매핑하는 역할을 하는데, 쉽게 말하면 SQLite 의 기능을 모두 사용할 수 있고, DB 로의 접근을 편하게 도와주는 라이브러리이다.


### Room Component 구성 요소
<img width="500" src="/room/sample/component.png">
- Entity: DB 안에 있는 테이블을 Java/Kotlin 클래스로 나타낸 것으로, 데이터 모델 클래스라고 볼 수 있다.<br/>
- DAO(Database Access Object): DB 에 접근해서 실질적으로 insert, delete 등을 수행하는 메소드를 포함한다.<br/>
- Database: Database Holder 를 포함하며, 앱에 영구 저장되는 데이터와 기본 연결을 위한 주 엑세스 지점이다. 또한 RoomDatabase 를 상속하는 추상 클래스여야 하며, 테이블과 버전을 정의하는 곳이다.
<br/>
<br/>



## 2. Room Usage

### Entity

```kotlin
/**
 * - Entity 는 DB 에 저장될 데이터의 형식으로 적어도 하나 이상의 Primary Key 가 필요하다.
 * - @PrimaryKey(autoGenerate = true) 를 이용해 자동으로 생성되게 하는 것도 가능하다.
 */
@Entity(tableName = "bookmark")
@TypeConverters(DateConverter::class)
data class BookMark(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "price")
    val price: String,

    @ColumnInfo(name = "dateTime")
    val dateTime: String,

    @ColumnInfo(name = "created")
    val created: Date
): Serializable {
    companion object {
        fun createBookMark(document: BookModel.Document): BookMark {
            return BookMark(
                thumbnail = document.thumbnail,
                title = document.title,
                price = document.sale_price,
                dateTime = document.datetime,
                created = Date()
            )
        }
    }
}
```


### DAO

```kotlin
/**
 * - DAO 는 DB 를 통해 수행할 작업을 정의한 클래스이다.
 * - 데이터의 삽입, 수정, 삭제 작업이나 저장된 데이터를 불러오는 작업 등을 함수 형태로 정의한다.
 * - Rx 에서 사용하는 Observable 로 받을 수 있다.
 * - OnConflictStrategy 는 Primary Key 가 겹칠 경우의 정책을 설정하는 부분이다.
 */
@Dao
interface BookMarkDao {

    @Query("SELECT * FROM bookmark ORDER BY created ASC")
    fun findAll(): DataSource.Factory<Int, BookMark>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookMark: BookMark)


    @Delete
    fun delete(bookMark: BookMark)
}
```


### Database

```kotlin
/**
 * - 데이터베이스 생성을 위한 추상클래스로 RoomDatabase() 를 상속한다.
 */
@Database(entities = [BookMark::class], version = DB_VERSION, exportSchema = false)
abstract class BookMarkDB: RoomDatabase() {

    abstract fun bookMarkDao(): BookMarkDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "bookMark.db"

        private var INSTANCE: BookMarkDB? = null

        fun getInstance(context: Context): BookMarkDB? {
            if (INSTANCE == null) {
                synchronized(BookMarkDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BookMarkDB::class.java,
                        DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
```
<br/>
<br/>


