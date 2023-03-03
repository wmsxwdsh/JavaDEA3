import com.company.dea.domain.InputCategories;
import com.company.dea.domain.InputData;
import com.company.dea.domain.Ordered;
import com.company.dea.domain.OutputCategories;
import com.company.dea.service.ModelFactory;
import config.CommonConstants;
import config.ParametersFilter;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author LZG
 */
public class MainApplication {

    public static void main(String[] args) {
        // 1、categories、coefficients
        InputCategories inputCategories = inputCategoriesAssemble();
        OutputCategories outputCategories = outputCategoriesAssemble();
        List<String> categories = getCategories();

        //2、入口数据
        Map<String, Double[]> coefficients = coefficientsAssembleV2();

        // 2.python上有参数校验 --- 略
        // has_same_dmus
        // validate_data
        // validate_weights_if_needed

        // 3、modelInput -- 组装Model 需要的数据
        InputData modelInput = constructInputDataInstance(categories, coefficients);

        // 4、modelInput 加上 inputCategories\outputCategories
        modelInput = ModelFactory.addInputAndOutputCategories(modelInput, inputCategories, outputCategories);

        //4、组装 model
        modelInput = build_models(inputCategories, outputCategories, modelInput);

    }

    /**
     * 建立模型
     * @param inputCategories
     * @param outputCategories
     * @param modelInput
     */
    public static InputData build_models(InputCategories inputCategories, OutputCategories outputCategories, InputData modelInput) {
        modelInput = ModelFactory.addInputAndOutputCategories(modelInput, inputCategories, outputCategories);
        String returnToScale = CommonConstants.RETURN_TO_SCALE;
        List<Map<String, String >> listOfParamObjects = new ArrayList();
        List<String> originalParams = ParametersFilter.VALID_PARAM_NAMES;
        /**
         *  去拼装界面上右侧Options 相关参数，这里固定写死了
         */
        Map<String, String> paramMap = new HashMap(20);
        for (String key : originalParams) {
            paramMap.put(key, "");
        }
        String inputCategoryNames = StringUtils.join(modelInput.getInputCategories().toArray(), ",");
        String outputCategoryNames = StringUtils.join(modelInput.getOutputCategories().toArray(), ",");
        paramMap.put("INPUT_CATEGORIES", inputCategoryNames);
        paramMap.put("OUTPUT_CATEGORIES", outputCategoryNames);
        paramMap.put("DEA_FORM", "env");
        paramMap.put("RETURN_TO_SCALE", returnToScale);
        paramMap.put("ORIENTATION", CommonConstants.ORIENTATION);
        listOfParamObjects.add(paramMap);

        return modelInput;
    }

    /**
     * 类目组装 input
     */
    public static InputCategories inputCategoriesAssemble() {
        InputCategories inputCategories = new InputCategories();
        inputCategories.setJiuYeRenYuanShu(true);
        return inputCategories;
    }

    /**
     * 类目组装 output
     */
    public static OutputCategories outputCategoriesAssemble() {
        OutputCategories outputCategories = new OutputCategories();
        outputCategories.setNengYuanXiaoHaoZongLiang(true);
        outputCategories.setDanWeiTanPaiGdp(true);
        outputCategories.setDanWeiTanPaiRenJunKeZhiPeiShouRu(true);
        return outputCategories;
    }

    /**
     * 数据封装。Excel 的9条原数据
     *
     * @return
     */
    public static Map<String, Ordered> coefficientsAssemble() {
        Ordered fuZhouShiOrdered = new Ordered("福州市", 420, 4893, 1892.44635560432, 25.8041397460445, 298956.37500166, 3840.77);
        Ordered xiaMenShiOrdered = new Ordered("厦门市", 428, 4308.26602, 1634.322885, 230.2256386, 520266.6907, 2519.83);
        Ordered puTianShiOrdered = new Ordered("莆田市", 329, 4473.890458, 1331.199771, 112.0798303, 98793.1339, 1362.33);
        Ordered sanMingOrdered = new Ordered("三明市", 351, 4394.952301, 1985.677357, 225.4072844, 677218.5107, 1401.9);
        Ordered quanZhouOrdered = new Ordered("泉州市", 380, 4641.962524, 1498.609296, 200.3042682, 228220.6195, 5808.14);
        Ordered zhangZhouOrdered = new Ordered("漳州市", 302, 4001.433175, 1370.296036, 25.23521457, 126592.6624, 2056.79);
        Ordered nanPingOrdered = new Ordered("南平市", 326, 4736.165241, 1781.424775, 248.6498277, 37568.96606, 759.42);
        Ordered longYanOrdered = new Ordered("龙岩市", 469, 4923.419708, 1897.060747, 268.4400788, 669204.3063, 1263.37);
        Ordered ningDeOrdered = new Ordered("宁德市", 350, 4730.675586, 1730.010957, 191.7037177, 476103.7355, 1319.69);

        Map<String, Ordered> orderedMap = new HashMap<>(10);
        orderedMap.put(fuZhouShiOrdered.getCityName(), fuZhouShiOrdered);
        orderedMap.put(xiaMenShiOrdered.getCityName(), xiaMenShiOrdered);
        orderedMap.put(puTianShiOrdered.getCityName(), puTianShiOrdered);
        orderedMap.put(sanMingOrdered.getCityName(), sanMingOrdered);
        orderedMap.put(quanZhouOrdered.getCityName(), quanZhouOrdered);
        orderedMap.put(zhangZhouOrdered.getCityName(), zhangZhouOrdered);
        orderedMap.put(nanPingOrdered.getCityName(), nanPingOrdered);
        orderedMap.put(longYanOrdered.getCityName(), longYanOrdered);
        orderedMap.put(ningDeOrdered.getCityName(), ningDeOrdered);

        return orderedMap;
    }

    /**
     * 数据封装。Excel 的9条原数据 V2 -- 从数据库获取
     *
     * @return
     */
    public static Map<String, Double[]> coefficientsAssembleV2() {
        Double[] fuZhouArray = new Double[]{420.0, 4893.0, 1892.446355604323, 25.8041397460445, 298956.37500166, 3840.77};
        Double[] xiaMenArray = new Double[]{428.0, 4308.2660197248215, 1634.3228845732976, 230.225638627013, 520266.690706769, 2519.83};
        Double[] puTianArray = new Double[]{329.0, 4473.890458161742, 1331.1997710668527, 112.079830273387, 98793.1339026389, 1362.33};
        Double[] sanMingArray = new Double[]{351.0, 4394.952300587259, 1985.6773572811744, 225.407284380401, 677218.510707515, 1401.9};
        Double[] quanZhouArray = new Double[]{380.0, 4641.962524074846, 1498.6092956424186, 200.304268175016, 228220.619475678, 5808.14};
        Double[] zhangZhouArray = new Double[]{302.0, 4001.4331748848404, 1370.29603627505, 25.2352145709681, 126592.662444429, 2056.79};
        Double[] nanPingArray = new Double[]{326.0, 4736.165241413146, 1781.4247748820385, 248.649827727603, 37568.9660591015, 759.42};
        Double[] longYanArray = new Double[]{469.0, 4923.419707607438, 1897.060747389653, 268.440078845532, 669204.306250648, 1263.37};
        Double[] ningDeArray = new Double[]{350.0, 4730.675586097547, 1730.0109565581897, 191.703717654035, 476103.735451561, 1319.69};

        Map<String, Double[]> coefficientsMap = new HashMap<>(10);
        coefficientsMap.put("福州市", fuZhouArray);
        coefficientsMap.put("厦门市", xiaMenArray);
        coefficientsMap.put("莆田市", puTianArray);
        coefficientsMap.put("三明市", sanMingArray);
        coefficientsMap.put("泉州市", quanZhouArray);
        coefficientsMap.put("漳州市", zhangZhouArray);
        coefficientsMap.put("南平市", nanPingArray);
        coefficientsMap.put("龙岩市", longYanArray);
        coefficientsMap.put("宁德市", ningDeArray);
        return coefficientsMap;
    }

    /**
     * 类目名称
     *
     * @return
     */
    public static List<String> getCategories() {
        String[] categoriesArray = new String[]{"就业人员数", "固定资产投资", "能源消耗总量", "单位碳排人口", "单位碳排GDP", "单位碳排人均可支配收入"};
        return Arrays.asList(categoriesArray);
    }

    /**
     * 模型 InputData
     */
    public static InputData constructInputDataInstance(List<String> categories, Map<String, Double[]> coefficients) {
        InputData inputData = new InputData();
        for (Map.Entry<String, Double[]> entry : coefficients.entrySet()) {
            String dmu = entry.getKey();
            Double[] list_of_coefficients = entry.getValue();
            int count = list_of_coefficients.length;
            for (int i = 0; i < count; i++) {
                inputData = addCoefficient(inputData, dmu, (String) categories.toArray()[count], list_of_coefficients[i]);
            }
        }

        return inputData;
    }

    /**
     * @param inputData
     * @param dmu
     * @param categoryName
     * @param value
     */
    private static InputData addCoefficient(InputData inputData, String dmu, String categoryName, Double value) {
        // _generate_next_DMU_code -- 用 dmu_ + redis自增id 应对并发
        String dmu_code = "dmu_" + inputData.getDmuCodes().size();

        // 校验是否已经配对，略
        // String[] dmuCategoryTurple = new String[]{dmu_code, categoryName};

        // Pair ({dmu}, {category}) is already recorded
        Map<String[], Double> coefficientsMap = new HashMap<>(1);
        coefficientsMap.put(new String[]{dmu_code, categoryName}, value);

        if (!inputData.getDmuCodes().contains(dmu_code)) {
            inputData.getDmuCodesInAddedOrder().add(dmu_code);
        }

        inputData.getDmuCodes().add(dmu_code);
        inputData.getCategories().add(categoryName);
        return inputData;
    }

    public static void runMethod() {

    }

}
