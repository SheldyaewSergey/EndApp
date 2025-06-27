package com.example.endapp

data class Word(
    val original: String,
    val translated: String,
    var learned: Boolean = false,
)

data class Question(
    val variants: List<Word>,
    val correctAnswer: Word,
)

class LearnWordTrainer{

    private val dictionary = listOf(
        Word("Toothbrush", "Зубная щётка"),
        Word("Toothpaste", "Зубная паста"),
        Word("Towel", "Полотенце"),
        Word("Shampoo", "Шампунь"),
        Word("Soap", "Мыло"),
        Word("Mirror", "Зеркало"),
        Word("Comb", "Расчёска"),
        Word("Hairdryer", "Фен"),
        Word("Toilet", "Туалет"),
        Word("Sink", "Раковина"),
        Word("Bathtub", "Ванна"),
        Word("Phone", "Телефон"),
        Word("Television", "Телевизор"),
        Word("Remote", "Пульт"),
        Word("Lamp", "Лампа"),
        Word("Clock", "Часы"),
        Word("Bed", "Кровать"),
        Word("Pillow", "Подушка"),
        Word("Blanket", "Одеяло"),
        Word("Mattress", "Матрас"),
        Word("Chair", "Стул"),
        Word("Table", "Стол"),
        Word("Couch", "Диван"),
        Word("Curtains", "Шторы"),
        Word("Carpet", "Ковёр"),
        Word("Bookshelf", "Книжная полка"),
        Word("Book", "Книга"),
        Word("Notebook", "Блокнот"),
        Word("Pen", "Ручка"),
        Word("Pencil", "Карандаш"),
        Word("Eraser", "Ластик"),
        Word("Bag", "Сумка"),
        Word("Backpack", "Рюкзак"),
        Word("Laptop", "Ноутбук"),
        Word("Computer", "Компьютер"),
        Word("Mouse", "Мышь"),
        Word("Keyboard", "Клавиатура"),
        Word("Monitor", "Монитор"),
        Word("Speaker", "Колонка"),
        Word("Headphones", "Наушники"),
        Word("Charger", "Зарядное устройство"),
        Word("Plug", "Вилка (электрическая)"),
        Word("Socket", "Розетка"),
        Word("Light", "Свет"),
        Word("Bulb", "Лампочка"),
        Word("Fan", "Вентилятор"),
        Word("Air conditioner", "Кондиционер"),
        Word("Refrigerator", "Холодильник"),
        Word("Microwave", "Микроволновка"),
        Word("Oven", "Духовка"),
        Word("Stove", "Плита"),
        Word("Kettle", "Чайник"),
        Word("Pan", "Сковорода"),
        Word("Pot", "Кастрюля"),
        Word("Cup", "Чашка"),
        Word("Mug", "Кружка"),
        Word("Plate", "Тарелка"),
        Word("Bowl", "Миска"),
        Word("Fork", "Вилка"),
        Word("Knife", "Нож"),
        Word("Spoon", "Ложка"),
        Word("Napkin", "Салфетка"),
        Word("Trash can", "Мусорное ведро"),
        Word("Broom", "Метла"),
        Word("Dustpan", "Совок"),
        Word("Vacuum cleaner", "Пылесос"),
        Word("Washing machine", "Стиральная машина"),
        Word("Laundry basket", "Корзина для белья"),
        Word("Iron", "Утюг"),
        Word("Ironing board", "Гладильная доска"),
        Word("Clothes", "Одежда"),
        Word("Shirt", "Рубашка"),
        Word("Trousers", "Брюки"),
        Word("Socks", "Носки"),
        Word("Shoes", "Обувь"),
        Word("Jacket", "Куртка"),
        Word("Hat", "Шляпа"),
        Word("Glasses", "Очки"),
        Word("Watch", "Наручные часы"),
        Word("Wallet", "Кошелёк"),
        Word("Keys", "Ключи"),
        Word("Door", "Дверь"),
        Word("Window", "Окно"),
        Word("Handle", "Ручка"),
        Word("Floor", "Пол"),
        Word("Ceiling", "Потолок"),
        Word("Wall", "Стена"),
        Word("Painting", "Картина"),
        Word("Picture", "Фотография"),
        Word("Calendar", "Календарь"),
        Word("Notebook", "Ноутбук/тетрадь"),
        Word("Scissors", "Ножницы"),
        Word("Glue", "Клей"),
        Word("Envelope", "Конверт"),
        Word("Printer", "Принтер"),
        Word("Paper", "Бумага"),
        Word("Folder", "Папка"),
        Word("Ruler", "Линейка"),
        Word("Stapler", "Степлер")
    )

    private var currentQuestion: Question? = null

    fun getNextQuestin(): Question? {

        val notLearnedList = dictionary.filter {!it.learned}
        if(notLearnedList.isEmpty()) return null

        val questionWords =
            if (notLearnedList.size < NUMBER_OF_ANSWERS) {
                val learnedList = dictionary.filter {it.learned}.shuffled()
                notLearnedList.shuffled()
                    .take(NUMBER_OF_ANSWERS) + learnedList
                    .take(NUMBER_OF_ANSWERS - learnedList.size)
            }else{
                notLearnedList.shuffled().take(NUMBER_OF_ANSWERS)
            }.shuffled()

        val correctAnswer = questionWords.random()

        currentQuestion = Question(
            variants = questionWords,
            correctAnswer = correctAnswer
        )
        return currentQuestion
    }

    fun checkAnswer(userAnswerIndex: Int?): Boolean {
        return currentQuestion?.let {

            val correctAnswerId = it.variants.indexOf(it.correctAnswer)
            if (correctAnswerId == userAnswerIndex){
                it.correctAnswer.learned = true
                true
            }else {
                false
            }
        } ?: false
    }
}

const val NUMBER_OF_ANSWERS: Int = 4