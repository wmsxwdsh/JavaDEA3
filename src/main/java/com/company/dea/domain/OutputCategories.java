package com.company.dea.domain;

import lombok.Data;

/**
 * @author LZG
 */
@Data
public class OutputCategories {

    private Boolean jiuYeRenYuanShu;
    private String jiuYeRenYuanShuInterpret = "就业人员数";

    private Boolean guDingZiChanTouZi;
    private String guDingZiChanTouZiInterpret = "固定资产投资";

    private Boolean nengYuanXiaoHaoZongLiang;
    private String nengYuanXiaoHaoZongLiangInterpret = "能源消耗总量";

    private Boolean danWeiTanPaiRenKou;
    private String danWeiTanPaiRenKouInterpret = "单位碳排人口";

    private Boolean danWeiTanPaiGdp;
    private String danWeiTanPaiGdpInterpret = "单位碳排GDP";

    private Boolean danWeiTanPaiRenJunKeZhiPeiShouRu;
    private String danWeiTanPaiRenJunKeZhiPeiShouRuInterpert = "单位碳排人均可支配收入";

}
