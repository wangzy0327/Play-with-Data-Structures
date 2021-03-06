Trie树的定义，发音（Tree-E）

![Trie](02-Trie-Basics/Trie.png)



Trie的删除操作

![Trie](Optional-02-Trie-Delete/Trie-delete.png)



### 基于哈希表或者数组的Trie

可能有些同学会尝试一下使用我们在这一章所讲的Trie当做映射，和二分搜索树作为底层映射实现，进行一定的性能比较。比较以后，可能会发现，使用我们这一章实现的Trie，性能优势似乎并没有那么明显，远没有Trie的理论复杂度分析所展现的那样有优势。

### 任务

我们在这一章所实现的Trie，效率相对比较低的核心原因，是我们的Trie的Node节点中，使用了TreeMap进行字符和节点的映射。TreeMap的底层实现是红黑树，虽然从复杂度分析的角度，红黑树是很高效的（各项操作均为O(logn)），但是，一定要注意，复杂度分析关注的是n趋于无穷的情况。对于小样本数据，一个复杂的复杂度更优的算法，很有可能比不上一个简单的复杂度更低的算法。因为常数项的不同。

如果你曾经看过我的《算法与数据结构》，就一定会有印象，在我们编写的归并排序和快速排序算法中，对于n比较小的情况，我们会转而使用理论复杂度更高的插入排序去优化。其中也是这个原因。虽然插入排序的理论复杂度比归并排序，快速排序都要高，但是只有在n很大的情况才能显现出来，在n很小的情况下，插入排序更快：）

我们在这一章的Trie中使用的TreeMap（红黑树）也是同理。红黑树中由于有复杂的旋转操作（在这个课程的第十三章会介绍），虽然整体平均复杂度更低，但是对于小样本数据，是没有太大的性能优势的。所以，在这里，其实，使用HashMap代替更好。如果你能保证你存储的单词只包含小写字母，直接使用数组将是更快的：）为了验证这一点，我编写了一套测试程序，可以参考课程的官方github，
**传送门：**[测试程序 https://github.com/liuyubobobo/Play-with-Data-Structures/tree/master/10-Trie/08-Trie-Using-HashMap-and-Array/src](https://github.com/liuyubobobo/Play-with-Data-Structures/tree/master/10-Trie/08-Trie-Using-HashMap-and-Array/src)

其中，

Trie 仍然是我们在这个课程中，内部结点使用TreeMap做映射的Trie；

Trie2 的内部结点中，使用HashMap做映射；

Trie3 的内部结点中，使用一个包含26个元素的数组做映射，这就限制了Trie3中存储的单词，只能包含小写字母。
在Main的测试用，我同时加载了《傲慢与偏见》和《双城记》两本书的单词，以稍微增大数据量。

在我的计算机上，一次典型的运行结果是这样的：）

Total different words: 12177
BSTSet: 0.464858395 s
Total different words: 12177
TreeMap Trie: 0.394749343 s
Total different words: 12177
HashMap Trie: 0.293205009 s
Total different words: 12177
Array(Map) Trie: 0.146712532 s
其中，

基于TreeMap的Trie将是最慢的：）

基于数组的Trie最快，因为相比HashMap，数组也不需要计算哈希函数：）（关于HashMap的内部原理，可以参见这个课程的第十四章）
注意：在这里，为了能让我们的第三个Trie可以运行，FileOperation的分词部分有所修改，只判断isLetter是不够的，因为双城记中包括法文字符，所以我自己写了一个isEnglishLetter的方法，相信聪明的你一看就懂：）

有兴趣可以下载一下我的测试代码，看看在你的环境下是否有和我的环境下类似的结果：）


注意：对于Trie来说，其实还有一个重要的时间开销。由于Trie消耗的空间比较大（每个节点会有若干个指针），所以给这些承载指针的空间（TreeMap，HashMap或者数组）开辟内存也是一个额外的时间消耗：）

看来真的是有得必有失啊：）

大家加油！：）