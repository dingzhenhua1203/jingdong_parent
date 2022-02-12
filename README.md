# jingdong_parent

#### 介绍

#### 软件架构
软件架构说明
1. 实体映射工具MapStruct 取代BeanUtils工具
```
 @Mapper
   public interface CarMapper {

   CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

   @Mapping(target = "seatCount", source = "numberOfSeats")
   CarDto carBoToCarDto(Car car);
   }
```

#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx