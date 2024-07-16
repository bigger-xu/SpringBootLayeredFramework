package com.cto.testing.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderFactory;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

/**
 *
 * @author ZhangYongWei
 * @since 2024/7/15
 */
public class NERTrainer {
    
    public static void main(String[] args) throws IOException {
        // 读取训练数据
        InputStreamFactory in = () -> Files.newInputStream(new File("CustomDictionary.txt").toPath());
        
        ObjectStream<String> lineStream = new PlainTextByLineStream(in, "UTF-8");
        ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);
        
        // 设置训练参数
        TrainingParameters params = new TrainingParameters();
        params.put(TrainingParameters.ITERATIONS_PARAM, 70);
        params.put(TrainingParameters.CUTOFF_PARAM, 1);
        
        // 训练模型
        TokenNameFinderModel model = NameFinderME.train("en", null, sampleStream, params, new TokenNameFinderFactory());
        
        // 保存模型
        File modelFile = new File("ner-model.bin");
        FileOutputStream modelOut = new FileOutputStream(modelFile);
        model.serialize(modelOut);
    }
}
