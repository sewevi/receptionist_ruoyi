package com.receptionist.util;

import com.github.pagehelper.Page;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.receptionist.dto.page.BasePageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List工具类
 *
 * @author dzhiqin@linewell.com
 * @since 2019-03-18
 */
@Slf4j
public class ListUtils {

    /**
     * 判断列表是否为空
     *
     * @param list 列表
     * @return 判断结果
     */
    public static boolean isEmpty(Collection list) {
        if (list != null) {
            return list.size() == 0;
        } else {
            return true;
        }
    }

    /**
    *@Description 获取2个list想同的值
    *@Param [list1, list2]
    *@Return java.util.List<T>
    *@Author xdayi@linewell.com
    *@Date 2020/7/8
    */
    public static <T,E> List<T> getTheSameSection(List<T> list1,List<E> list2) {
        List dto = new ArrayList();
        for (E t:list2) {
            if (list1.contains(t)) {
                dto.add(t);
            }
        }
        return dto;
    }

    /**
     * 判断列表是否不为空
     *
     * @param list 列表
     * @return 判断结果
     */
    public static boolean isNotEmpty(Collection list) {
        return !isEmpty(list);
    }

    /**
     * 判断区域权限是否为小区帐号权限
     *
     * @param authAreaList 区域权限列表
     * @return 判断结果
     */
    public static boolean isVillage(List<String> authAreaList) {
        return authAreaList != null && authAreaList.size() == 1 && authAreaList.get(0).length() == 15;
    }

    /**
     * 获取区域权限正则表达式
     *
     * @param authAreaList 区域权限列表
     * @return 正则表达式
     */
    public static String getAuthAreaRegexp(List<String> authAreaList) {
        return authAreaList != null ? "^" + String.join("|^", authAreaList) : null;
    }

    /**
     * 判断区域权限是否为全国管理员
     *
     * @param authAreaList 区域权限列表
     * @return 判断结果
     */
    public static boolean isNation(List<String> authAreaList) {
        return StringUtils.isSonOfCodeList("0", authAreaList);
    }

    /**
     * 返回单位编码（先判断是否为小区帐号再使用该方法）
     *
     * @param authAreaList 区域权限列表
     * @return 单位编码
     */
    public static String getVillageCode(List<String> authAreaList) {
        return authAreaList != null ? authAreaList.get(0) : null;
    }

    /**
     * 对象拷贝 - 对象数组的拷贝
     * @param src       源数组
     * @param clazz     目标 .class
     * @param <T>       源数组中对象的类型
     * @param <R>       目标类型
     * @return          新的目标类型数组
     */
    // public static <T, R> List<R> copyList(List<T> src, Class<R> clazz) {
    //     if (Objects.isNull(src) || Objects.isNull(clazz) || src.isEmpty()) {
    //         return new ArrayList<>();
    //     }
    //     List<R> targets = new ArrayList<>(src.size());
    //     try {
    //         for (T s : src) {
    //             R c = (R) ReflectUtil.newInstance(clazz);
    //             BeanUtils.copyProperties(s, c);
    //             targets.add(c);
    //         }
    //     } catch (Exception e) {
    //         log.error("[CopyListProperties] 拷贝出错：" + e.getMessage());
    //         throw new RuntimeException("数据出错");
    //     }
    //     return targets;
    // }

    /**
     * 单个类转换
     *
     * @param sourceObj
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T conver(Object sourceObj, Class<T> targetClass) {
        if (sourceObj == null || targetClass == null) {
            return null;
        }
        T targetObj = null;
        try {
            targetObj = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("sourceObj:{},targetClass:{}", sourceObj, targetClass, e);
            return null;
        }
        BeanUtils.copyProperties(sourceObj, targetObj);
        return targetObj;
    }

    /**
     * List之间转换
     *
     * @param sourceList
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> List<T> converList(List<?> sourceList, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(sourceList) || targetClass == null) {
            return Collections.emptyList();
        }
        return sourceList.stream().map(sourceObj -> conver(sourceObj, targetClass)).collect(Collectors.toList());
    }

    /**
     * 分页basePageDTO初始化
     * @param dto
     * @param listSize
     * @param pageBool
     * @param page
     */
    public static void basePageDTOInit(BasePageDTO dto, int listSize, boolean pageBool, Page page){
        if (pageBool){
            // 设置PageInfo
            dto.setTotal(page.getTotal());
        }else {
            // 设置PageInfo
            dto.setTotal((long) listSize);
        }
    }
}
