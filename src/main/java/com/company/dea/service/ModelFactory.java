package com.company.dea.service;

import com.company.dea.domain.InputCategories;
import com.company.dea.domain.InputData;
import com.company.dea.domain.OutputCategories;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LZG
 * @date 2023.01.09
 */
public class ModelFactory {

    /**
     *
     * @param modelInput
     * @param inputCategories
     * @param outputCategories
     * @return
     */
    public static InputData addInputAndOutputCategories(InputData modelInput, InputCategories inputCategories, OutputCategories outputCategories) {
        Set<String> inputCategoryNameSet = new HashSet<>();
        if(inputCategories.getJiuYeRenYuanShu()) {
            inputCategoryNameSet.add(inputCategories.getJiuYeRenYuanShuInterpret());
        }
        if(inputCategories.getGuDingZiChanTouZi()) {
            inputCategoryNameSet.add(inputCategories.getGuDingZiChanTouZiInterpret());
        }
        if(inputCategories.getNengYuanXiaoHaoZongLiang()) {
            inputCategoryNameSet.add(inputCategories.getNengYuanXiaoHaoZongLiangInterpret());
        }
        if(inputCategories.getDanWeiTanPaiRenKou()) {
            inputCategoryNameSet.add(inputCategories.getDanWeiTanPaiRenKouInterpret());
        }
        if(inputCategories.getDanWeiTanPaiGdp()) {
            inputCategoryNameSet.add(inputCategories.getDanWeiTanPaiGdpInterpret());
        }
        if(inputCategories.getDanWeiTanPaiRenJunKeZhiPeiShouRu()) {
            inputCategoryNameSet.add(inputCategories.getDanWeiTanPaiRenJunKeZhiPeiShouRuInterpert());
        }

        Set<String> outCategoryNameSet = new HashSet<>();
        if(outputCategories.getJiuYeRenYuanShu()) {
            outCategoryNameSet.add(inputCategories.getJiuYeRenYuanShuInterpret());
        }
        if(outputCategories.getGuDingZiChanTouZi()) {
            outCategoryNameSet.add(inputCategories.getGuDingZiChanTouZiInterpret());
        }
        if(outputCategories.getNengYuanXiaoHaoZongLiang()) {
            outCategoryNameSet.add(inputCategories.getNengYuanXiaoHaoZongLiangInterpret());
        }
        if(outputCategories.getDanWeiTanPaiRenKou()) {
            outCategoryNameSet.add(inputCategories.getDanWeiTanPaiRenKouInterpret());
        }
        if(outputCategories.getDanWeiTanPaiGdp()) {
            outCategoryNameSet.add(inputCategories.getDanWeiTanPaiGdpInterpret());
        }
        if(outputCategories.getDanWeiTanPaiRenJunKeZhiPeiShouRu()) {
            outCategoryNameSet.add(inputCategories.getDanWeiTanPaiRenJunKeZhiPeiShouRuInterpert());
        }

        if(0 == inputCategoryNameSet.size() || 0 == outCategoryNameSet.size()) {
            // 进这一步，直接返回吧，不符合model计算的基本条件
            throw new RuntimeException("Both input and output categories must be specified");
        }
        modelInput.setInputCategories(inputCategoryNameSet);
        modelInput.setInputCategories(outCategoryNameSet);

        return modelInput;
    }

}
