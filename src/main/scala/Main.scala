import scala.io.Source

object Main extends App{
  val n = 20 //заданное n

  //Простые числа меньше заданного n
  def filter(lazyList: LazyList[Int]) : LazyList[Int] = {
    lazyList.head #:: filter(lazyList.tail.filter(_ % lazyList.head != 0))
  }

  val simpleNumber = filter(LazyList.from(2)).take(n).toList
  println(s"Простые числа меньше $n : ${simpleNumber.mkString(" ")}")

  //n-ное число Фибоначчи
    //Первый вариант решения
  val fibList: LazyList[Int] =  1 #:: fibList.scanLeft(1)(_ + _)
  println(s"$n-ое число Фибоначчи = ${fibList.take(n).last}")
  
    //Второй вариант решения
  def fib(n: Int) : Long = {
    var prev: Long = 1
    var next: Long = 1
    for (_ <- 3 to n) {
      val temp = prev + next
      prev = next
      next = temp
    }
    next
  }
  println(s"$n-ое число Фибоначчи = ${fib(n)}")

  //Все перестановки и сочетания для заданного набора чисел
  val source = List("do", "did", "done")
  println("Перестановки: \n" + source.permutations.mkString("\n"))
  println("Комбинации: \n" + source.combinations(2).mkString("\n"))

  //Расчет остатков груза на складе
  val filename = ".\\src\\main\\resources\\cargo.txt"
  val file = Source.fromFile(filename)
  val cargoInWarehouse = file.getLines().toList.foldLeft(0)(_+_.toInt)
  println(s"На складе сейчас ${cargoInWarehouse}")
  file.close()

}
