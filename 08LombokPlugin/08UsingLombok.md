LOMBOK
· 一个java library
· 一个plugin插件
· 一个build tool
· 在类上放置注解

使用步骤
1.在IDEA中安装Lombok插件
file -> setting -> plugin 搜索Lombok (安装完一定要重启才能生效)
2.在项目pom.xml中导入依赖 lombok包(为了演示 不在父工程里导入 只在该工程导入)
3.可以使用的注解
@Getter and @Setter [掌握]
@FieldNameConstants 
@ToString [掌握]
@EqualsAndHashCode [掌握]
@AllArgsConstructor [掌握], @RequiredArgsConstructor and @NoArgsConstructor[掌握]
@Log, @Log4j, @Log4j2, @Slf4j, @XSlf4j, @CommonsLog, @JBossLog, @Flogger, @CustomLog
@Data [掌握]
@Builder
@SuperBuilder
@Singular
@Delegate
@Value
@Accessors [掌握]
@Wither
@With
@SneakyThrows

4.使用(查看IDEA 的 structure窗口可以查看)
@Data加在实体类上会产生 
无参构造方法 canEqual equals hashCode toString 以及各属性的get set方法

@AllArgsConstructor 加在实体类上会产生  全参构造方法
@NoArgsConstructor  加在实体类上会产生  无参构造方法 可以同时加上
也可以使用自定义的构造方法, 进行互补

@Getter @Setter可以加载实体类上 也可以加载实体类的属性上
加在实体类上, 会产生所有属性的get set方法
加载属性上, 只会产生对应属性的get set方法
查看Getter源码 @Target({ElementType.FIELD, ElementType.TYPE})
既可以作用在属性ElementType.FIELD上也可以作用在类上ElementType.TYPE