package com.company.dea.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author LZG
 */
@Data
@AllArgsConstructor
public class Ordered {

    /**
     * 模型组装时生成，如果 List<Ordered> 数据从数据库拿，dmu_code 可用 id
     */
//    private String dmu_code;

    private String cityName;

    private double jiuYeRenYuanShu;

    private double guDingZiChanTouZi;

    private double nengYuanXiaoHaoZongLiang;

    private double danWeiTanPaiRenKou;

    private double danWeiTanPaiGdp;

    private double danWeiTanPaiRenJunKeZhiPeiShouRu;
}
