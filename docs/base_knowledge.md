# Java基础知识

## 1,java中的反射：

   JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意方法和属性；这种动态获取信息以及动态调用对象方法的功能称为java语言的反射机制。
   java中的反射首先是能够获取java中要反射类的字节码，获取字节码有三种方法（1，Class.forName(className);2,类名.class;3,this.getClass()）然后将字节码中的方法，变量，构造函数等映射成相应的Method、Filed、Constructor等类，这些类提供了丰富的方法可以被我们所使用。

## 2,泛型

(1),什么是java泛型？
泛型是Java SE 1.5的新特性，泛型的本质是参数化类型，也就是说所操作的数据类型被指定为一个参数。这种参数类型可以用在类、接口和方法的创建中，分
别称为泛型类、泛型接口、泛型方法。

(2),为什么需要泛型？
Java语言引入泛型的好处是安全简单。可以将运行时错误提前到编译时错误。
在Java SE 1.5之前，没有泛型的情况的下，通过对类型Object的引用来实现参数的“任意化”，“任意化”带来的缺点是要做显式的强制类型转换，而这种转
换是要求开发者对实际参数类型可以预知的情况下进行的。对于强制类型转换错误的情况，编译器可能不提示错误，在运行的时候才出现异常，这是一个安全
隐患。泛型的好处是在编译的时候检查类型安全，并且所有的强制转换都是自动和隐式的，提高代码的重用率。

## 3,Java通过Executors提供四种线程池，分别为：

（1），newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
（2），newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
（3），newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
（4），newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

## 4,多线程的实现方式：

（1），继承Thread类，
（2），实现Runnable接口，
（3），使用ExecutorService、Callable、Future实现有返回结果的多线程

## 5,什么是线程同步？

当使用多个线程来访问同一个数据时，非常容易出现线程安全问题(比如多个线程都在操作同一数据导致数据不一致),所以我们用同步机制来解决这些问题。

## 6,实现同步机制有两个方法：

(1),同步代码块：
synchronized(同一个数据){} 同一个数据：就是N条线程同时访问一个数据。
(2),同步方法：
public synchronized 数据返回类型 方法名(){}

## 7,Java中wait()和sleep()方法的区别：

（1），sleep（）是Thread类的方法，是线程用来 控制自身流程的比如有一个要报时的线程，每一秒中打印出一个时间，那么我就需要在print方法前面加上一个sleep让自己每隔一秒执行一次。就像个闹钟一样，wait（）是Object类的方法，用来线程间的通信，这个方法会使当前拥有该对象锁的进程等待直到其他线程调用notify（）方法时再醒来，不过你也可以给他指定一个时间，自动醒来。这个方法主要是用作不同线程之间的调度的。
（2）最大的区别是：在等待是wait（）会释放锁而sleep（）一直持有锁，wait通常用于线程间交互，sleep通常被用于暂停执行

## 8,java中的设计模式：

   java中的设计模式一般认为有23中，总体来说分为三大类：
   （1），创建型模式 5种：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式；
   （2），结构型模式 7种：适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式；
   （3），行为型模式 11种：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。

## 9,List容器和Set容器的区别

   List :  (1)有序的数据，取数据的时候是按放数据的顺序取的；（2）可重复的数据。
   Set:    (1)无序的数据，取数据的顺序和放数据的顺序不一致； (2)不可重复的数据(通过equals和hashcode共同决定的)。

- equals() 和 hashcode() 区别：
   equals()是指二者逻辑意义上相等即可,而 hashcode() 则是对一个对象进行hash计算得到的一个散列值;
   - hashcode() 特点：
     (1),对象x和y的hashcode相同，不代表两个对象就相同(x.equals(y)=true)，可能存在hash碰撞；不过hashcode如果不相同，那么一定是两个不同的对象;(2),如果两个对象的equals()相等，那么hashcode一定相等。
     所以我们一般可以用hashcode来快速比较两个对象互异，因为如果x.hashcode() != y.hashcode()，那么x.equals(y)=false。
   - equals() 的特性
     (1),自反性：对于非null的对象x，必须有 x.equals(x)=true;(2),对称性：如果 x.equals(y)=true，那么y.equals(x)必须也为true;(3),传递性：如果x.equals(y)=true而且y.equals(z)=true，那么x.equals(z)必须为true;(4),对于非null的对象x，一定有x.equals(null)=false

## 10,Iterable （独立接口没有父接口）

java中增强for循环用于那些类型的对象

1，数组

2，Iterable类型的实例

## 11,Map （独立没有父接口）

Map类型的容器不能直接用增强for循环

Map容器的特点 ：保存的关键字和值对

取Map 类型的信息有几种 ：

(1),取所有的关键字 keySet;(2),取所有的值 values;(3),取所有的关键字和值对 entrySet

## 12,Collection与Collections区别

  Collection 是Iterable的一个接口
  collections 是一个类

## 13,switch 语句可以使用的数据类型：   

byte int short char  enum只有这五种类型

## 14，面向对象 

|      |     权限     |  本类  | 同包子类 | 非同包子类 | 非同包非子类（没关系） |
| :--: | :--------: | :--: | :--: | :---: | :---------: |
|      |  private   |  ok  |      |       |             |
|  默认  | (friendly) |  ok  |  ok  |       |             |
|      | protected  |  ok  |  ok  |  ok   |             |
|      |   public   |  ok  |  ok  |  ok   |     ok      |

三大特征：封装   多态   继承

(1),封装   ----------------属性私有化，定义public访问方法

(2),多态

	用父类声明的变量引用子类的对象
	用接口声明的变量来引用实现该接口的类的对象
	1，jdk1.5以后，用Object声明的变量可以引用java中任何数据类型，java中所有的数据都可以调用Object类的方法（数组也可以）
	Object类的用法
	   Object 方法：equals   hashcode()   toString()  finalize()垃圾回收   clone  wait()  notify  notifyAll getClass 
	   String类  常量
		String  StringBuffer(线程安全) StringBuilder（线程非安全的）
- 实现多态主要有以下三种方式: 
  - 接口实现 
  - 继承父类重写方法 
  - 同一类中进行方法重载
- 多态的定义：指允许不同类的对象对同一消息做出响应。即同一消息可以根据发送对象的不同而采用多种不同的行为方式。（发送消息就是函数调用）
- 多态的作用：消除类型之间的耦合关系。
- 虚拟机是如何实现多态的:动态绑定（dynamic binding），在执行期间判断所引用对象的实 际类型，根据其实际的类型调用其相应的方法。
- Java中多态的实现方式：接口实现，继承父类进行方法重写，同一个类中进行方法重载。
- 多态的好处:
  - 1.可替换性（substitutability）。多态对已存在代码具有可替换性。例如，多态对圆Circle类工作，对其他任何圆形几何体，如圆环，也同样工作。
  - 2.可扩充性（extensibility）。多态对代码具有可扩充性。增加新的子类不影响已存在类的多态性、继承性，以及其他特性的运行和操作。实际上新加子类更容易获得多态功能。例如，在实现了圆锥、半圆锥以及半球体的多态基础上，很容易增添球体类的多态性。
  - 3.接口性（interface-ability）。多态是超类通过方法签名，向子类提供了一个共同接口，由子类来完善或者覆盖它而实现的。如图8.3 所示。图中超类Shape规定了两个实现多态的接口方法，computeArea()以及computeVolume()。子类，如Circle和Sphere为了实现多态，完善或者覆盖这两个接口方法。
  - 4.灵活性（flexibility）。它在应用中体现了灵活多样的操作，提高了使用效率。
    5.简化性（simplicity）。多态简化对应用软件的代码编写和修改过程，尤其在处理大量对象的运算和操作时，这个特点尤为突出和重要。

(3),继承

	（1），java是单继承，继承具有传递性（访问权限）
	（2），构造函数，子类一定要调用父类构造函数，默认调用父类默认的构造函数，如果父类默认构造，子类必须调用父类定义的带参数的构造函数
## 15,方法覆盖

       （1），访问权限不能比父类的弱
       （2），返回类型 jdk1.4 返回类型必须完全一样，jdk1.5以后 返回类型可以是父类方法返回类型的子类类型
       （3），方法名必须一样
       （4），参数必须一样
       （5），不能抛出父类方法没有抛出的异常（如果父类方法没有抛出异常，子类覆盖方法一定不能抛出异常，如果父类方法抛出异常，子类方法可以不抛出异常，如果抛出异常，必须父类方法抛出的异常能够识别的（父子关系））
## 16,方法重载 

方法名一样参数不一样，调用的时候，看传递的实际参数的类型，去找对应的最匹配的重载方法。

如果定义类没有写extends   ，那么 默认 extends Object, 所有的类类型都是Object类子类

## 17,实例化顺序

	1，先初始化父类静态属性或静态初始化块（只执行一次）
	    2，初始化子类静态属性或静态初始化块（只执行一次）
	    3，为父类子类属性分配空间并初始化默认（按类型初始化）
	    4，父类属性或者非静态块的初始化（按顺序）
	    5，父类构造函数的执行
	    6，子类属性或非静态块的初始化（按顺序）
	    7，子类构造函数的执行
   属性和静态方法看变量的类型，如果是父类类型调用父类，如果是子类类型调用子类，非静态方法看对象，子类对象调用子类的，父类对象调用父类的。
   属性先静态属性或者静态块按顺序执行（只执行一次），非静态属性或者非静态块按顺序初始化（执行多次）
   对象调用方法，如果存在方法覆盖调用子类的 否则调用父类的

## 18,接口和抽象类

- 接口的特点：

  （1），接口不能实例化;

  （2），接口只能声明抽象方法或常量;

  （3），接口里所有的访问权限都是public;

  （4），接口可以多继承

  - 接口的意义 : 规范、扩展、回调

- 抽象类的特点：

  - （1），抽象类可以有非抽象方法；
  - （2），抽象类不能实例化；
  - （3），抽象类里有四种访问权限；
  - （4），抽象类是单继承

- 抽象类的意义 : 

  - 为其子类提供一个公共的类型 
  - 封装子类中的重复内容 
  - 定义抽象方法，子类虽然有不同的实现 但是定义是一致的

 当描述一组方法的时候使用接口

 当描述一个虚拟的物体的时候使用抽象类

## 19,内部类

- 1，成员内部类
- 2，静态内部类（顶层内部类）
- 3，方法中的内部类
- 4，匿名类（匿名抽象类或者接口，非抽象类）

内部类作用：

- (1),内部类可以用多个实例，每个实例都有自己的状态信息，并且与其他外围对象的信息相互独立。
- (2),在单个外围类中，可以让多个内部类以不同的方式实现同一个接口，或者继承同一个类。
- (3),创建内部类对象的时刻并不依赖于外围类对象的创建。
- (4),内部类并没有令人迷惑的“is-a”关系，他就是一个独立的实体。
- (5),内部类提供了更好的封装，除了该外围类，其他类都不能访问。

## 20,父类的静态方法能否被子类重写

不能.

子类继承父类后，用相同的静态方法和非静态方法，这时非静态方法覆盖父类中的方法（即方法重写），父类的该静态方法被隐藏（如果对象是父类则调用该隐藏的方法），另外子类可继承父类的静态与非静态方法，至于方法重载我觉得它其中一要素就是在同一类中，不能说父类中的什么方法与子类里的什么方法是方法重载的体现

## 21,java虚拟机的特性

Java语言的一个非常重要的特点就是与平台的无关性。而使用Java虚拟机是实现这一特点的关键。一般的高级语言如果要在不同的平台上运行，至少需要编译成不同的目标代码。而引入Java语言虚拟机后，Java语言在不同平台上运行时不需要重新编译。Java语言使用模式Java虚拟机屏蔽了与具体平台相关的信息，使得Java语言编译程序只需生成在Java虚拟机上运行的目标代码（字节码），就可以在多种平台上不加修改地运行。Java虚拟机在执行字节码时，把字节码解释成具体平台上的机器指令执行。

## 22,哪些情况下的对象会被垃圾回收机制处理掉

Java 垃圾回收机制最基本的做法是分代回收。内存中的区域被划分成不同的世代，对象根据其存活的时间被保存在对应世代的区域中。一般的实现是划分成3个世代：年轻、年老和永久。内存的分配是发生在年轻世代中的。当一个对象存活时间足够长的时候，它就会被复制到年老世代中。对于不同的世代可以使用不同的垃圾回收算法。进行世代划分的出发点是对应用中对象存活时间进行研究之后得出的统计规律。一般来说，一个应用中的大部分对象的存活时间都很短。比如局部变量的存活时间就只在方法的执行过程中。基于这一点，对于年轻世代的垃圾回收算法就可以很有针对性。

## 23,进程和线程的区别

简而言之,一个程序至少有一个进程,一个进程至少有一个线程。

线程的划分尺度小于进程，使得多线程程序的并发性高。

另外，进程在执行过程中拥有独立的内存单元，而多个线程共享内存，从而极大地提高了程序的运行效率。

线程在执行过程中与进程还是有区别的。每个独立的线程有一个程序运行的入口、顺序执行序列和程序的出口。但是线程不能够独立执行，必须依存在应用程序中，由应用程序提供多个线程执行控制。

从逻辑角度来看，多线程的意义在于一个应用程序中，有多个执行部分可以同时执行。但操作系统并没有将多个线程看做多个独立的应用，来实现进程的调度和管理以及资源分配。这就是进程和线程的重要区别。

进程是具有一定独立功能的程序关于某个数据集合上的一次运行活动,进程是系统进行资源分配和调度的一个独立单位.

线程是进程的一个实体,是CPU调度和分派的基本单位,它是比进程更小的能独立运行的基本单位.线程自己基本上不拥有系统资源,只拥有一点在运行中必不可少的资源(如程序计数器,一组寄存器和栈),但是它可与同属一个进程的其他的线程共享进程所拥有的全部资源.

一个线程可以创建和撤销另一个线程;同一个进程中的多个线程之间可以并发执行.

进程和线程的主要差别在于它们是不同的操作系统资源管理方式。进程有独立的地址空间，一个进程崩溃后，在保护模式下不会对其它进程产生影响，而线程只是一个进程中的不同执行路径。线程有自己的堆栈和局部变量，但线程之间没有单独的地址空间，一个线程死掉就等于整个进程死掉，所以多进程的程序要比多线程的程序健壮，但在进程切换时，耗费资源较大，效率要差一些。但对于一些要求同时进行并且又要共享某些变量的并发操作，只能用线程，不能用进程。如果有兴趣深入的话，我建议你们看看《现代操作系统》或者《操作系统的设计与实现》。对就个问题说得比较清楚。

## 24,java int与integer的区别

(1), int与integer的区别从大的方面来说就是基本数据类型与其包装类的区别

(2), int 是基本类型，直接存数值，而integer是对象，用一个引用指向这个对象

## 25,string-stringbuffer-stringbuilder区别

(1),String 字符串常量
(2),StringBuffer 字符串变量（线程安全）
(3),StringBuilder 字符串变量（非线程安全）

## 26,Java 类动态加载动态类加载：

动态加载一个类十分简单。你要做的就是获取一个类加载器然后调用它的loadClass()方法；
Java程序可以动态扩展是由运行期动态加载和动态链接实现的；比如：如果编写一个使用接口的应用程序，可以等到运行时再指定其实际的实现(多态)，解析过程有时候还可以在初始化之后执行；比如：动态绑定(多态).

## 27,Java中==与equals的区别

==比较两个对象在内存里是不是同一个对象，就是说在内存里的存储位置一致。两个String对象存储的值一样的，但有可能在内存里存储在不同地方。

## 28,final,finally,finalize的区别

- final:常量声明;
- finally:异常处理;
- finalize:帮助进行垃圾回收

## 29,列举java的集合和继承关系

![image](https://github.com/Victor2018/FlowFunny/raw/master/SrceenShot/collection.png)

## 30,Hashmap和Hashtable的区别

- 继承不同
  Hashtable是基于陈旧的Dictionary类，完成了Map接口；HashMap是Java 1.2引进的Map接口的一个实现（HashMap继承于AbstractMap,AbstractMap完成了Map接口）。
- 线程安全不同
  HashTable的方法是同步的，HashMap是未同步，所以在多线程场合要手动同步HashMap
- 对null的处理不同
  HashTable不允许null值(key和value都不可以),HashMap允许null值(key和value都可以)。即 HashTable不允许null值其实在编译期不会有任何的不一样，会照样执行，只是在运行期的时候Hashtable中设置的话回出现空指
  针异常。 HashMap允许null值是指可以有一个或多个键所对应的值为null。当get()方法返回null值时，即可以表示 HashMap中没有该键，也可以表示该键所对应的值为null。因此，在HashMap中不能由get()方法来判断
  HashMap中是否存在某个键，而应该用containsKey()方法来判断。
- 方法不同
  HashTable有一个contains(Object value)，功能和containsValue(Object value)功能一样。
- 哈希值的使用不同
  HashTable直接使用对象的hashCode。而HashMap重新计算hash值。 
  -遍历方式的内部实现上不同
  Hashtable、HashMap都使用了 Iterator。而由于历史原因，Hashtable还使用了Enumeration的方式 。 
- 内部实现方式的数组的初始大小和扩容的方式不一样 
  HashTable中的hash数组初始大小是11，增加的方式是 old*2+1。HashMap中hash数组的默认大小是16，而且一定是2的指数。

|  HashMap  | 线程不安全 | 允许有null的键和值  | 效率高一点 | 方法不是Synchronize的要提供外同步 | 有containsvalue和containsKey方法 | HashMap是Java1.2引进的Map interface的一个实现 | HashMap是Hashtable的轻量级实现 |
| :-------: | :---: | :----------: | :---: | :--------------------: | :--------------------------: | :----------------------------------: | :---------------------: |
| Hashtable | 线程安全  | 不允许有null的键和值 | 效率稍低  |    方法是Synchronize的     |        有contains方法方法         |       Hashtable 继承于Dictionary类       |  Hashtable比HashMap 要旧   |

## 31,java中堆和栈的区别

### 各司其职

最主要的区别就是栈内存用来存储局部变量和方法调用。
而堆内存用来存储Java中的对象。无论是成员变量，局部变量，还是类变量，它们指向的对象都存储在堆内存中。

### 独有还是共享

栈内存归属于单个线程，每个线程都会有一个栈内存，其存储的变量只能在其所属线程中可见，即栈内存可以理解成线程的私有内存。
而堆内存中的对象对所有线程可见。堆内存中的对象可以被所有线程访问。

### 异常错误

如果栈内存没有可用的空间存储方法调用和局部变量，JVM会抛出java.lang.StackOverFlowError。
而如果是堆内存没有可用的空间存储生成的对象，JVM会抛出java.lang.OutOfMemoryError。

### 空间大小

栈的内存要远远小于堆内存，如果你使用递归的话，那么你的栈很快就会充满。如果递归没有及时跳出，很可能发生StackOverFlowError问题。
你可以通过-Xss选项设置栈内存的大小。-Xms选项可以设置堆的开始时的大小，-Xmx选项可以设置堆的最大值。

# Android基础知识

## 1，Activity启动模式:

(1),standard:系统默认的模式，每次创建一个Activity时，都会在栈中创建一个实例。也就是说不管栈中这个实例是否存在都一定会创建一个新的实例。这个模式下，谁启动了Activity，那这个Activity就运行在启动它的那个Activity的任务栈中;

(2),singleTop:栈顶复用模式。该模式下，如果新的Activity已经位于栈顶，那么则不会创建新的实例，同时会回调onNewIntent()方法，**需要注意，这时这个新的Activity的onCreate(),onStart()不会被调用，因为它并没有发生。但是若新的Activity没有位于栈顶，还是会创建一个新的实例;

(3),singleTask:栈内复用模式。这是一种单例模式，如果任务栈中有新Activity的实例，则不会创建，同时回调onNewIntent()方法。具体来讲，若新的Activity所处的任务栈的不存在，则创建一个新的任务栈，并创建实例压入栈中。 若存在Activity所需要的任务栈，当前栈中若不存在该Activity的实例则创建，否则将存在的实例放在栈顶并调用onNewIntent();

(4),singleInstance:单实例模式，这是一个加强的singleTask模式。Activity独占一个任务栈，就它一个，其他后续的请求均不会创建新的Activity，除非该任务栈被销毁。

## 2,Android Service两种启动方式

（1），Context.startService()方式启动；

（2），Context.bindService()方式启动。（生命周期与提供 context者一致）

![image](https://github.com/Victor2018/FlowFunny/raw/master/SrceenShot/start_service.png)

## 3,Android中的几种动画

Android3.0之前有2种，3.0后有3种。

(1),FrameAnimation（逐帧动画）：传统的动画方法，通过顺序的播放排列好的图片来实现，类似电影。

(2),TweenAnimation（补间动画）：这种实现方式可以使视图组件移动、缩放、旋转及产生透明变化。

(3),PropertyAnimation（属性动画）：属性动画不再仅仅是一种视觉效果了，而是一种不断地对值进行操作的机制，并将值赋到指定对象的指定属性上，可以是任意对象的任意属性。

## 4,Android中跨进程通讯的几种方式

- (1),Intent传递数据

  访问其他应用程序的Activity 如调用系统通话应用
  Intent callIntent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:12345678");
  startActivity(callIntent);

- (2),Content Provider 如访问系统相册

- (3),Messenger方式

- (4),AIDL服务

- (5),Socket通信

- (6),文件共享

- (7),广播（Broadcast） 如显示系统时间

## 5,如何让android的service一直在后台运行？

(1),把service和activity分开，让service开机启动。设置一个broadcast receiver接受开机信号，使用RECEIVE_BOOT_COMPLETED的permission， 然后启动service。activity启动后绑定到service上，通过ipc机制通信，acitivity结束后松绑。注意安装后要手动启动service，不会自动启动，之后重启手机后才会随开机启动。

(2),在内存低的时候系统会自动清理进程，这时候后台service可能会被杀掉。可以在onStartCommand中返回START_STICKY，这样系统有足够多资源的时候，就会重新开启service。

## 6,Android中横竖屏切换会调用生命周期的哪些方法？

此时的生命周期跟清单文件里面的配置有关

（1），不设置activity的android:configChanges时，切屏会重新调用各个生命周期，默认首先销毁当前activity然后重新加载；

onPause()  - > onStop()  - > onDestroy()  - >  onCreate()  - > onStart()   - >  onResume()

（2），设置activity的android:configChanges="orientation|keyboardHidden|screenSize"，切屏不会重新调用各个生命周期，只会执行onConfigurationChanged方法。

## 7,什么情况下回导致OOM异常?

- （1），图片过大导致OOM;（1，等比例缩小图片；2，对图片采用软引用，及时地进行recyle()操作）
- （2），界面切换到OOM; 
  - 1，看看布局中有没有大的图片，比如背景图，去除xml中的设置改在程序中设置背景图（放在onCreate（）方法中）；
  - 2，跟方法1相似，直接把xml配置文件加载成view再放到一个容器里，然后直接调用this.setContentView(View view),避免xml的重复加载；
  - 3，在页面切换时尽可能少的重复使用一些代码；
- （3），查询数据库没有关闭游标（用完游标及时关闭）；
- （4），构造Adapter时，没有使用缓存的convertView（尽可能的使用converView）；
- （5），Bitmap对象不再使用时，调用recyle（）释放内存；

## 8,Android屏幕适配方式有哪些？

- （1），适配方式之dp；
- （2），适配方式之dimens；
- （3），适配方式之layout；
- （4），适配方式之java代码适配；
- （5），适配方式之weight权重适配。

## 9,View绘制流程：

（1），mesarue()过程：主要作用：为整个View树计算实际的大小，即设置实际的高(对应属性:mMeasuredHeight)和宽(对应属性:mMeasureWidth)，每个View的控件的实际宽高都是由父视图和本身视图决定的。

（2），layout布局过程：主要作用 ：为将整个根据子视图的大小以及布局参数将View树放到合适的位置上。

（3）， draw()绘图过程：由ViewRoot对象的performTraversals()方法调用draw()方法发起绘制该View树，值得注意的是每次发起绘图时，并不会重新绘制每个View树的视图，而只会重新绘制那些“需要重绘”的视图，View类内部变量包含了一个标志位DRAWN，当该视图需要重绘时，就会为该View添加该标志位。

## 10,handler机制的原理:

andriod提供了Handler 和 Looper 来满足线程间的通信。Handler先进先出原则。Looper类用来管理特定线程内对象之间的消息交换(MessageExchange)。

- Looper: 一个线程可以产生一个Looper对象，由它来管理此线程里的MessageQueue(消息队列)。
- Handler: 你可以构造Handler对象来与Looper沟通，以便push新消息到MessageQueue里;或者接收Looper从Message Queue取出)所送来的消息。
- Message Queue(消息队列):用来存放线程放入的消息。 
- 线程：UIthread 通常就是main thread，而Android启动程序时会替它建立一个MessageQueue。

## 11,Touch事件的传递

在 Android 中，事件是沿着链条传递的，所以还需要关注事件的流向。

首先，最先接收到触摸事件的是 Activity，然后再 RootView，然后再沿着 View 树从上到下传递，事件一直传递到树的最底部，之后事件会反过来由底向上传递。

但是如果在事件的传递过程，某一个 View 或者 ViewGroup 对这个事件感兴趣，那么事件传递就在此终止，之后的其余事件就会也在这个 View 或者 ViewGroup 停止了，也就是我们常说的事件被消耗了。

如果事件一直传递到底部，但是没有任何 View 对事件感兴趣，此事件就会在 View 树中一直向上传递，直到这个事件返回 Activity 为止。

某一个 View 或者 ViewGroup 对这个事件感兴趣，只需要将它的 onTouchEvent() 方法的返回值设为 true。之后，系统就知道了某一个 View 或者 ViewGroup 对这个事件感兴趣，于是就中断原本的触摸事件的响应链。

所以，一般情况下，当手指按下，也就是 ACTION_DOWN 事件传递时，各方法执行路线如下

- 事件从上到下传递:Activity.dispatchEvent() --> ViewGroup.dispatchEvent() --> ViewGroup.onInterceptTouchEvent() -- > ViewGroup.dispatchEvent() --> ViewGroup.onInterceptTouchEvent()  --> ... --> View.dispatchEvent()
- 事件从下到上返回:View.onTouchEvent() --> ViewGroup.onTouchEvent() --> ... --> ViewGroup.onTouchEvent() --> Activity.onTouchEvent()

## 12，Service与Activity之间通信的几种方式

(1),通过Binder对象
(2),通过broadcast(广播)的形式
(3),通过观察者模式

## 13，介绍不同场景下Activity生命周期的变化过程

启动Activity： onCreate()—>onStart()—>onResume()，Activity进入运行状态。
Activity退居后台： 当前Activity转到新的Activity界面或按Home键回到主屏： onPause()—>onStop()，进入停滞状态。
Activity返回前台： onRestart()—>onStart()—>onResume()，再次回到运行状态。
Activity退居后台，且系统内存不足， 系统会杀死这个后台状态的Activity，若再次回到这个Activity,则会走onCreate()–>onStart()—>onResume()
锁定屏与解锁屏幕 只会调用onPause()，而不会调用onStop方法，开屏后则调用onResume()

## 14，理解Activity，View,Window三者关系

Activity像一个工匠（控制单元），Window像窗户（承载模型），View像窗花（显示视图）LayoutInflater像剪刀，Xml配置像窗花图纸。
1：Activity构造的时候会初始化一个Window，准确的说是PhoneWindow。
2：这个PhoneWindow有一个“ViewRoot”，这个“ViewRoot”是一个View或者说ViewGroup，是最初始的根视图。
3：“ViewRoot”通过addView方法来一个个的添加View。比如TextView，Button等
4：这些View的事件监听，是由WindowManagerService来接受消息，并且回调Activity函数。比如onClickListener，onKeyDown等。

## 15，注册广播的几种方法?

(1),静态注册:在清单文件中注册， 常见的有监听设备启动，常驻注册不会随程序生命周期改变

(2),动态注册:在代码中注册，随着程序的结束，也就停止接受广播了


## 16,Fragment 和 Activity之间如何传值？

当Fragment 和 Activity绑定之后，在Fragment中可以通过getActivity()方法获取到其绑定的Ativity对象，这样就可以调用Activity的方法了，在Activity中可以通过FragmentManager fm = getFragmentManager();Fragment frag = fm.findFragmentByTag(tag),   Fragment frag = fm.findFragmentById(id)获取到Fragment之后就可以调用Fragment的方法，也就实现了通信功能。

## 17,Android如何捕获未捕获的异常？

（1），自定义一个Application，比如叫MyApplicateion 继承Application 实现UncaughtExceptionHandler;

（2），覆写实现UncaughtExceptionHandler的onCreate()和实现UncaughtException（）方法
   注意：在onCreate()中我们给Thread类设置默认异常处理handler，如果这句代码（Thread.setDefaultUncaughtExceptionHandler(this)）不执行则一切都是白搭；

在实现UncaughtException（）方法中我们必须新开辟一个线程进行我们的异常搜集工作，然后将系统给杀死；

（3），在AndroidMainiFest中配置该Application。

## 18,如何修改activity进入和退出动画？

（1），自定义activity主题；
（2），覆写activity的overridePendingTransition方法

overridePendingTransition（R.anim.fade,R.anim.hold）;

## 19,数据类型

| 基本类型 | byte | short |   int   | long | float | double | boolean |   char    |
| :--: | :--: | :---: | :-----: | :--: | :---: | :----: | :-----: | :-------: |
|  封包  | Byte | Short | Integer | Long | Float | Double | Boolean | Character |
| 默认值  |  0   |   0   |    0    |  0   |  0.0  |  0.0   |  flase  | '\u0000'  |

除了八种基本类型，一切皆对象（隐用类型  默认值是null）
Enum    枚举类型

## 20，标识符定义

（1），由字母，数字，下划线（_），美元符（$）四种组成

（2），不能以数字开头

（3），长度不受限制          oracle 中长度受限制（最长30 个字节）

（4），不能和java中的关键字冲突

## 21，static

static成员都是属于类管理，所有的对象共享

static属性或初始化 只执行一次  

- 1，第一次调用该类静态成员
- 2，第一次实例化该类
- 3，执行该类（执行main方法之前）

static关键字只能用于类成员，不能修饰独立的类

## 22，abstract  

- 修饰类，也可以修饰类成员
- 修饰类表示该类是抽象类,但是该类可以没有抽象方法，并且也不能实例化
- 修饰方法，表示该方法是抽象的，那么如果一个类有一个抽象方法，那么该类必须声明成abstract类

## 23，final

修饰类和类的成员

- 修饰类，表示该类不能被别的类继承，常见的类：String  System Math 八种基本类型对应的包装类
- 修饰方法，表示该方法不能被覆盖。
- 修饰属性，表示该属性是常量

final 与 abstract 不能混用

static 与 abstract 也不能混用

访问权限private 与 abstract不能混用

当方法被private ,static ,final三个其中一个修饰的时候，该方法静态绑定

## 24，xml 语法

节点由元素节点、文本节点、属性节点组成

1， 只有一个根标记（节点）

2，节点由开始标记、结束标记  body部分组成

3，大小写区分

## 25，Touch事件的处理流程：

- 1,一旦某个ViewGroup获得了ACTION_DOWN的事件，会根据深度优先的算法遍历以该ViewGroup为根节点的view树
- 2,如果点击的位置在被遍历到的childView区域中，childView是groupview的话其onInterceptTouchEvent将被调用，这个过程会一直进行下去
- 3,直到某个viewX的onInterceptTouchEvent返回了true，或者一直遍历到某个叶viewX，然后该viewX的onTouchEvent被调用
- 4,如果viewX的onTouchEvent返回false，则该viewX的父节点viewY的onTouchEvent被调用，这个过程会一直下去
- 5,如果直到根节点的onTouchEvent都返回false，那么后续的ACTION_MOVE和ACTION_UP将被这棵树里面的所有的view忽略
- 6,当某个viewX在onTouchEvent中返回true后，ACTION_MOVE和ACTION_UP会从根节点开始按照深度优先的算法依次调用onInterceptTouchEvent，但是和ACTION_DOWN不同，该过程只持续到viewX的父节点为止，然后viewX的onInterceptTouchEvent不被调用，而直接调用onTouchEvent（查看实验3的日志）
- 7,如果在ACTION_MOVE或者ACTION_UP在被viewX的onTouchEvent处理之前，某个viewZ的onInterceptTouchEvent返回true，表明viewZ要处理接下来的触摸事件，那么在viewZ的onTouchEvent被调用之前，onInterceptTouchEvent调用还会继续下去，只不过之后的view的onInterceptTouchEvent中的ACTION变成了ACTION_CANCEL,直到viewX中的onTouchEvent处理了这个ACTION_CANCEL后，viewZ的onTouchEvent才被调用，后续事件将只被viewZ的onTouchEvent处理。

## 26,5个Android开发中比较常见的内存泄漏问题及解决办法
### 一、单例造成的内存泄漏

Android的单例模式非常受开发者的喜爱，不过使用的不恰当的话也会造成内存泄漏。因为单例的静态特性使得单例的生命周期和应用的生命周期一样长，这就说明了如果一个对象已经不需要使用了，而单例对象还持有该对象的引用，那么这个对象将不能被正常回收，这就导致了内存泄漏。

如下这个典例：
public class AppManager {
    private static AppManager instance;
    private Context context;
    private AppManager(Context context) {
        this.context = context;
    }
    public static AppManager getInstance(Context context) {
        if (instance != null) {
            instance = new AppManager(context);
        }
        return instance;
    }
}

这是一个普通的单例模式，当创建这个单例的时候，由于需要传入一个Context，所以这个Context的生命周期的长短至关重要：
- 1、传入的是Application的Context：这将没有任何问题，因为单例的生命周期和Application的一样长 ;


- 2、传入的是Activity的Context：当这个Context所对应的Activity退出时，由于该Context和Activity的生命周期一样长(Activity间接继承于Context)，所以当前Activity退出时它的内存并不会被回收，因为单例对象持有该Activity的引用。

所以正确的单例应该修改为下面这种方式：
public class AppManager {

    private static AppManager instance;
    private Context context;
    private AppManager(Context context) {
        this.context = context.getApplicationContext();
    }
    public static AppManager getInstance(Context context) {
        if (instance != null) {
            instance = new AppManager(context);
        }
        return instance;
    }
}

这样不管传入什么Context最终将使用Application的Context，而单例的生命周期和应用的一样长，这样就防止了内存泄漏。

### 二、非静态内部类创建静态实例造成的内存泄漏

有的时候我们可能会在启动频繁的Activity中，为了避免重复创建相同的数据资源，会出现这种写法：
public class MainActivity extends AppCompatActivity {

    private static TestResource mResource = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(mManager == null){
            mManager = new TestResource();
        }
        //...
    }
    class TestResource {
        //...
    }
}

这样就在Activity内部创建了一个非静态内部类的单例，每次启动Activity时都会使用该单例的数据，这样虽然避免了资源的重复创建，不过这种写法却会造成内存泄漏，因为非静态内部类默认会持有外部类的引用，而又使用了该非静态内部类创建了一个静态的实例，该实例的生命周期和应用的一样长，这就导致了该静态实例一直会持有该Activity的引用，导致Activity的内存资源不能正常回收。正确的做法为：
将该内部类设为静态内部类或将该内部类抽取出来封装成一个单例，如果需要使用Context，请使用ApplicationContext 。

### 三、Handler造成的内存泄漏:

Handler的使用造成的内存泄漏问题应该说最为常见了，平时在处理网络任务或者封装一些请求回调等api都应该会借助Handler来处理，对于Handler的使用代码编写一不规范即有可能造成内存泄漏。
如下示例：
public class MainActivity extends AppCompatActivity {
  private Handler mHandler = new Handler() {

```
  @Override
  public void handleMessage(Message msg) {
      //...
  }
```
  };
  @Override
  protected void onCreate(Bundle savedInstanceState) {
```
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  loadData();
```
  }
  private void loadData(){
```
  //...request
  Message message = Message.obtain();
  mHandler.sendMessage(message);
```
  }
}
这种创建Handler的方式会造成内存泄漏，由于mHandler是Handler的非静态匿名内部类的实例，所以它持有外部类Activity的引用，我们知道消息队列是在一个Looper线程中不断轮询处理消息，那么当这个Activity退出时消息队列中还有未处理的消息或者正在处理消息，而消息队列中的Message持有mHandler实例的引用，mHandler又持有Activity的引用，所以导致该Activity的内存资源无法及时回收，引发内存泄漏，所以另外一种做法为：
public class MainActivity extends AppCompatActivity {
  private MyHandler mHandler = new MyHandler(this);
  private TextView mTextView ;
  private static class MyHandler extends Handler {
```
  private WeakReference<Context> reference;
  public MyHandler(Context context) {
      reference = new WeakReference<>(context);
  }
  @Override
  public void handleMessage(Message msg) {
      MainActivity activity = (MainActivity) reference.get();
      if(activity != null){
          activity.mTextView.setText("");
      }
  }
```
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
```
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  mTextView = (TextView)findViewById(R.id.textview);
  loadData();
```
  }

  private void loadData() {
```
  //...request
  Message message = Message.obtain();
  mHandler.sendMessage(message);
```
  }
}
创建一个静态Handler内部类，然后对Handler持有的对象使用弱引用，这样在回收时也可以回收Handler持有的对象，这样虽然避免了Activity泄漏，不过Looper线程的消息队列中还是可能会有待处理的消息，所以我们在Activity的Destroy时或者Stop时应该移除消息队列中的消息，更准确的做法如下：
public class MainActivity extends AppCompatActivity {
  private MyHandler mHandler = new MyHandler(this);
  private TextView mTextView ;
  private static class MyHandler extends Handler {
```
  private WeakReference<Context> reference;
  public MyHandler(Context context) {
      reference = new WeakReference<>(context);
  }
  @Override
  public void handleMessage(Message msg) {
      MainActivity activity = (MainActivity) reference.get();
      if(activity != null){
          activity.mTextView.setText("");
      }
  }
```
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
```
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  mTextView = (TextView)findViewById(R.id.textview);
  loadData();
```
  }

  private void loadData() {
```
  //...request
  Message message = Message.obtain();
  mHandler.sendMessage(message);
```
  }

  @Override
  protected void onDestroy() {
```
  super.onDestroy();
  mHandler.removeCallbacksAndMessages(null);
```
  }
}
使用mHandler.removeCallbacksAndMessages(null);是移除消息队列中所有消息和所有的Runnable。当然也可以使用mHandler.removeCallbacks();或mHandler.removeMessages()；来移除指定的Runnable和Message。

### 四、线程造成的内存泄漏：

对于线程造成的内存泄漏，也是平时比较常见的，如下这两个示例可能每个人都这样写过：
//——————test1

```
  new AsyncTask<Void, Void, Void>() {
      @Override
      protected Void doInBackground(Void... params) {
          SystemClock.sleep(10000);
          return null;
      }
  }.execute();
```
//——————test2
```
  new Thread(new Runnable() {
      @Override
      public void run() {
          SystemClock.sleep(10000);
      }
  }).start();
```
上面的异步任务和Runnable都是一个匿名内部类，因此它们对当前Activity都有一个隐式引用。如果Activity在销毁之前，任务还未完成， 那么将导致Activity的内存资源无法回收，造成内存泄漏。正确的做法还是使用静态内部类的方式，如下：
static class MyAsyncTask extends AsyncTask<Void, Void, Void> {
```
  private WeakReference<Context> weakReference;
```

```
  public MyAsyncTask(Context context) {
      weakReference = new WeakReference<>(context);
  }

  @Override
  protected Void doInBackground(Void... params) {
      SystemClock.sleep(10000);
      return null;
  }

  @Override
  protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      MainActivity activity = (MainActivity) weakReference.get();
      if (activity != null) {
          //...
      }
  }
```
  }
  static class MyRunnable implements Runnable{
```
  @Override
  public void run() {
      SystemClock.sleep(10000);
  }
```
  }
//——————
  new Thread(new MyRunnable()).start();
  new MyAsyncTask(this).execute();
这样就避免了Activity的内存资源泄漏，当然在Activity销毁时候也应该取消相应的任务AsyncTask::cancel()，避免任务在后台执行浪费资源。

### 五、资源未关闭造成的内存泄漏：

对于使用了BraodcastReceiver，ContentObserver，File，Cursor，Stream，Bitmap等资源的使用，应该在Activity销毁时及时关闭或者注销，否则这些资源将不会被回收，造成内存泄漏。


##27,APP启动过程-习习网络
[参考](http://www.jianshu.com/p/a72c5ccbd150)
![image](https://github.com/Victor2018/FlowFunny/raw/master/SrceenShot/app_launch.png)

- ①点击桌面App图标，Launcher进程采用Binder IPC向system_server进程发起startActivity请求；
- ②system_server进程接收到请求后，向zygote进程发送创建进程的请求；
- ③Zygote进程fork出新的子进程，即App进程；
- ④App进程，通过Binder IPC向sytem_server进程发起attachApplication请求；
- ⑤system_server进程在收到请求后，进行一系列准备工作后，再通过binder IPC向App进程发送scheduleLaunchActivity请求；
- ⑥App进程的binder线程（ApplicationThread）在收到请求后，通过handler向主线程发送LAUNCH_ACTIVITY消息；
- ⑦主线程在收到Message后，通过发射机制创建目标Activity，并回调Activity.onCreate()等方法。
- ⑧到此，App便正式启动，开始进入Activity生命周期，执行完onCreate/onStart/onResume方法，UI渲染结束后便可以看到App的主界面。


## 28,描述一次网络请求的流程-新浪
![image](https://github.com/Victor2018/FlowFunny/raw/master/SrceenShot/http.png)


## 29,LinearLayout和RelativeLayout性能对比-百度

- RelativeLayout会让子View调用2次onMeasure，LinearLayout 在有weight时，也会调用子View2次onMeasure
- RelativeLayout的子View如果高度和RelativeLayout不同，则会引发效率问题，当子View很复杂时，这个问题会更加严重。如果可以，尽量使用padding代替margin。
- 在不影响层级深度的情况下,使用LinearLayout和FrameLayout而不是RelativeLayout。

最后再思考一下文章开头那个矛盾的问题，为什么Google给开发者默认新建了个RelativeLayout，而自己却在DecorView中用了个LinearLayout。因为DecorView的层级深度是已知而且固定的，上面一个标题栏，下面一个内容栏。采用RelativeLayout并不会降低层级深度，所以此时在根节点上用LinearLayout是效率最高的。而之所以给开发者默认新建了个RelativeLayout是希望开发者能采用尽量少的View层级来表达布局以实现性能最优，因为复杂的View嵌套对性能的影响会更大一些。



## 30,什么是深拷贝和浅拷贝

浅拷贝：使用一个已知实例对新创建实例的成员变量逐个赋值，这个方式被称为浅拷贝。 深拷贝：当一个类的拷贝构造方法，不仅要复制对象的所有非引用成员变量值，还要为引用类型的成员变量创建新的实例，并且初始化为形式参数实例值。这个方式称为深拷贝

## 31，Handler,Thread和HandlerThread的差别-小米
从Android中Thread（java.lang.Thread -> java.lang.Object）描述可以看出，Android的Thread没有对Java的Thread做任何封装，但是Android提供了一个继承自Thread的类HandlerThread（android.os.HandlerThread -> java.lang.Thread），这个类对Java的Thread做了很多便利Android系统的封装。

android.os.Handler可以通过Looper对象实例化，并运行于另外的线程中，Android提供了让Handler运行于其它线程的线程实现，也是就HandlerThread。HandlerThread对象start后可以获得其Looper对象，并且使用这个Looper对象实例Handler。

## 32，本地广播和全局广播有什么差别

因广播数据在本应用范围内传播，不用担心隐私数据泄露的问题。 不用担心别的应用伪造广播，造成安全隐患。 相比在系统内发送全局广播，它更高效。

## 33,View刷新机制-百度-美团

由ViewRoot对象的performTraversals()方法调用draw()方法发起绘制该View树，值得注意的是每次发起绘图时，并不会重新绘制每个View树的视图，而只会重新绘制那些“需要重绘”的视图，View类内部变量包含了一个标志位DRAWN，当该视图需要重绘时，就会为该View添加该标志位。

调用流程 ：

mView.draw()开始绘制，draw()方法实现的功能如下：

绘制该View的背景
为显示渐变框做一些准备操作(见5，大多数情况下，不需要改渐变框)          
调用onDraw()方法绘制视图本身   (每个View都需要重载该方法，ViewGroup不需要实现该方法)
调用dispatchDraw ()方法绘制子视图(如果该View类型不为ViewGroup，即不包含子视图，不需要重载该方法)值得说明的是，ViewGroup类已经为我们重写了dispatchDraw ()的功能实现，应用程序一般不需要重写该方法，但可以重载父类函数实现具体的功能。

## 34,Android中的ANR异常如何分析又该怎么去避免ANR
在Android中，应用程序的响应是由Activity Manager和WindowManager系统服务监视的 。当它监测到A、B、C情况中的一个时，Android就会针对特定的应用程序显示ANR：
- A.在5秒内没有响应输入的事件（例如，按键按下，屏幕触摸）--主要类型
- B.BroadcastReceiver在10秒内没有执行完毕
- C.Service在特定时间内（20秒内）无法处理完成--小概率类型

怎么避免ANR:

- (1)避免在主线程上进行复杂耗时的操作，比如说发送接收网络数据/进行大量计算/操作数据库/读写文件等。这个可以通过使用AsyncTask或者使用多线程来实现。
- (2)broadCastReceiver 要进行复杂操作的的时候，可以在onReceive()方法中启动一个Service来处理
- (3)在设计及代码编写阶段避免出现出现同步/死锁或者错误处理不恰当等情况。


## 使用过的框架、平台：

1，EventBus（事件处理）；
2，Jpush（消息推送）；
3，友盟（统计平台）；
4，有米（优米）（广告平台）；
5，百度地图；
6，ShareSDK;
7，Vitamio（适配播放框架）；
8，爱加密（代码混淆平台）；
9,MPAndroidChart（图表框架）；
10，ZXing （二维码及条码扫描框架）
11，AsyncImageLoader（图片缓存框架）
12，Glide（图片缓存框架）
13,Gson(json数据解析)
14,Volley(Http请求)
15,okhttp(Http请求)

## 使用过的自定义控件：

1，XListView；
2，SlidingMenu;
3,HorizontalListview;
4,KenBurnsView;
5,PagerSlidingTabStrip;
6,CircleImageView;
7,SmoothCheckBox;
8,SquareProgressBar;
9,VerticalSeekBar;
10,WaveView;





