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
- 多态的定义：指允许不同类的对象对同一消息做出响应。即同一消息可以根据发送对象的不同而采用多种不同的行为方式。（发送消息就是函数调用）
- 多态的作用：消除类型之间的耦合关系。
- 实现多态的技术称为：动态绑定（dynamic binding），是指在执行期间判断所引用对象的实 际类型，根据其实际的类型调用其相应的方法。
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

  （1），抽象类可以有非抽象方法；

  （2），抽象类不能实例化；

  （3），抽象类里有四种访问权限；

  （4），抽象类是单继承

  - 抽象类的意义 : 为其子类提供一个公共的类型 封装子类中的重复内容 定义抽象方法，子类虽然有不同的实现 但是定义是一致的

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


- (3),广播（Broadcast） 如显示系统时间


- (4),AIDL服务


- (5),Socket通信
- (6),文件共享

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

## 16,Service和Activity通信

- 通过Binder
- 通过broadcast

## 17,Fragment 和 Activity之间如何传值？

当Fragment 和 Activity绑定之后，在Fragment中可以通过getActivity()方法获取到其绑定的Ativity对象，这样就可以调用Activity的方法了，在Activity中可以通过FragmentManager fm = getFragmentManager();Fragment frag = fm.findFragmentByTag(tag),   Fragment frag = fm.findFragmentById(id)获取到Fragment之后就可以调用Fragment的方法，也就实现了通信功能。

## 18,Android如何捕获未捕获的异常？

（1），自定义一个Application，比如叫MyApplicateion 继承Application 实现UncaughtExceptionHandler;

（2），覆写实现UncaughtExceptionHandler的onCreate()和实现UncaughtException（）方法
   注意：在onCreate()中我们给Thread类设置默认异常处理handler，如果这句代码（Thread.setDefaultUncaughtExceptionHandler(this)）不执行则一切都是白搭；

在实现UncaughtException（）方法中我们必须新开辟一个线程进行我们的异常搜集工作，然后将系统给杀死；

（3），在AndroidMainiFest中配置该Application。

## 19,如何修改activity进入和退出动画？

（1），自定义activity主题；
（2），覆写activity的overridePendingTransition方法

overridePendingTransition（R.anim.fade,R.anim.hold）;

## 20,数据类型

| 基本类型 | byte | short |   int   | long | float | double | boolean |   char    |
| :--: | :--: | :---: | :-----: | :--: | :---: | :----: | :-----: | :-------: |
|  封包  | Byte | Short | Integer | Long | Float | Double | Boolean | Character |
| 默认值  |  0   |   0   |    0    |  0   |  0.0  |  0.0   |  flase  | '\u0000'  |

除了八种基本类型，一切皆对象（隐用类型  默认值是null）
Enum    枚举类型

## 21，标识符定义

（1），由字母，数字，下划线（_），美元符（$）四种组成

（2），不能以数字开头

（3），长度不受限制          oracle 中长度受限制（最长30 个字节）

（4），不能和java中的关键字冲突

## 22，static

static成员都是属于类管理，所有的对象共享

static属性或初始化 只执行一次  

- 1，第一次调用该类静态成员
- 2，第一次实例化该类
- 3，执行该类（执行main方法之前）

static关键字只能用于类成员，不能修饰独立的类

## 23，abstract  

- 修饰类，也可以修饰类成员
- 修饰类表示该类是抽象类,但是该类可以没有抽象方法，并且也不能实例化
- 修饰方法，表示该方法是抽象的，那么如果一个类有一个抽象方法，那么该类必须声明成abstract类

## 24，final

修饰类和类的成员

- 修饰类，表示该类不能被别的类继承，常见的类：String  System Math 八种基本类型对应的包装类
- 修饰方法，表示该方法不能被覆盖。
- 修饰属性，表示该属性是常量

final 与 abstract 不能混用

static 与 abstract 也不能混用

访问权限private 与 abstract不能混用

当方法被private ,static ,final三个其中一个修饰的时候，该方法静态绑定

## 25，xml 语法

节点由元素节点、文本节点、属性节点组成

1， 只有一个根标记（节点）

2，节点由开始标记、结束标记  body部分组成

3，大小写区分

## 26，Touch事件的处理流程：

- 1,一旦某个ViewGroup获得了ACTION_DOWN的事件，会根据深度优先的算法遍历以该ViewGroup为根节点的view树
- 2,如果点击的位置在被遍历到的childView区域中，childView是groupview的话其onInterceptTouchEvent将被调用，这个过程会一直进行下去
- 3,直到某个viewX的onInterceptTouchEvent返回了true，或者一直遍历到某个叶viewX，然后该viewX的onTouchEvent被调用
- 4,如果viewX的onTouchEvent返回false，则该viewX的父节点viewY的onTouchEvent被调用，这个过程会一直下去
- 5,如果直到根节点的onTouchEvent都返回false，那么后续的ACTION_MOVE和ACTION_UP将被这棵树里面的所有的view忽略
- 6,当某个viewX在onTouchEvent中返回true后，ACTION_MOVE和ACTION_UP会从根节点开始按照深度优先的算法依次调用onInterceptTouchEvent，但是和ACTION_DOWN不同，该过程只持续到viewX的父节点为止，然后viewX的onInterceptTouchEvent不被调用，而直接调用onTouchEvent（查看实验3的日志）
- 7,如果在ACTION_MOVE或者ACTION_UP在被viewX的onTouchEvent处理之前，某个viewZ的onInterceptTouchEvent返回true，表明viewZ要处理接下来的触摸事件，那么在viewZ的onTouchEvent被调用之前，onInterceptTouchEvent调用还会继续下去，只不过之后的view的onInterceptTouchEvent中的ACTION变成了ACTION_CANCEL,直到viewX中的onTouchEvent处理了这个ACTION_CANCEL后，viewZ的onTouchEvent才被调用，后续事件将只被viewZ的onTouchEvent处理。

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





