package com.example.duplication.service;

import com.openai.client.OpenAIClient;
import com.openai.core.JsonField;
import com.openai.models.embeddings.Embedding;
import com.openai.models.embeddings.EmbeddingCreateParams;
import com.openai.models.embeddings.CreateEmbeddingResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmbeddingService {

    private static final String EMBEDDING_MODEL = "text-embedding-3-small";
    private final OpenAIClient client;

    public EmbeddingService(OpenAIClient client) {
        this.client = client;
    }

    public List<float[]> embedTexts(List<String> texts) {
        List<float[]> out = new ArrayList<>(texts.size());

        try {
            // Build parameters for embedding
            EmbeddingCreateParams params = EmbeddingCreateParams.builder()
                    .model(EMBEDDING_MODEL)
                    .input((JsonField<EmbeddingCreateParams.Input>) texts)
                    .build();

            // Most SDKs 4.6–4.8 return EmbeddingsResponse from this call
            CreateEmbeddingResponse res = client.embeddings().create(params);

            // Safely access data (some versions use .getData(), others .data())
            List<Embedding> embeddings;
            try {
                embeddings = res.data(); // Preferred
            } catch (NoSuchMethodError e) {
                embeddings = (List<Embedding>) res._data(); // Older versions
            }

            // Convert Double → float[] and collect
            for (Embedding e : embeddings) {
                List<Float> dv = e.embedding();
                float[] fv = new float[dv.size()];
                for (int i = 0; i < dv.size(); i++) fv[i] = dv.get(i).floatValue();
                out.add(fv);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create embeddings", e);
        }

        return out;
    }
}
