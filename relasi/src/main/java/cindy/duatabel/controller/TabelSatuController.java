package cindy.duatabel.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.google.gson.Gson;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.validation.Validated;
import cindy.duatabel.model.TabelSatu;
import cindy.duatabel.intfc.TabelSatuInterface;

@Validated
@Controller("/tabelsatu")
public class TabelSatuController {

    private final TabelSatuInterface tabelSatuInterface;

    TabelSatuController(final TabelSatuInterface tabelSatuInterface){
        this.tabelSatuInterface = tabelSatuInterface;  
    }
    
    @Get(produces = MediaType.APPLICATION_JSON)
    public String index(@Nullable @QueryValue final int page, @QueryValue final int limit){
        final HashMap<String, Object> data = new HashMap<>();
        try {
            final List<TabelSatu> tbsatu = tabelSatuInterface.findAll(page, limit);
            data.put("page", Math.ceil(tabelSatuInterface.size()/limit));
            data.put("status", "ok");
            data.put("message", "data tabel");
            data.put("data", tbsatu);
            return (new Gson().toJson(data));
        } catch (Exception e) {
            data.put("status", "error");
            data.put("message", e.getMessage());
            return (new Gson().toJson(data));
        }
    }
    
    @Get("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String show(@PathVariable @Nullable final Long id) {
        return (new Gson()).toJson(tabelSatuInterface.findById(id));
    }

 

    @Put(consumes=MediaType.APPLICATION_JSON)
    public String update(@Body TabelSatu tabelsatu) {
        HashMap<String, Object> data = new HashMap<>();
        if (tabelSatuInterface.update(tabelsatu.getId(), tabelsatu.getName(), tabelsatu.getEmail())) {
        // if (repository.updateCode(c.getId(), c.getCode())) {
            data.put("status", "ok");
        } else {
            data.put("status", "fail");
        }
        return (new Gson()).toJson(data);
    }

 
 
 
 
    @Post(consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String save(@Body final TabelSatu tbsatu) {
        final HashMap<String, Object> data = new HashMap<>();
        try {
            Long result = tabelSatuInterface.save(tbsatu);
            data.put("status", "ok");
            data.put("id", result);
        } catch(Exception e) {
            data.put("status", "fail");
            data.put("message", e.getMessage());
        }
        return (new Gson()).toJson(data);
    }

    @Delete("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String destroy(@PathVariable @Nullable final Long id) {
        final HashMap<String, Object> data = new HashMap<>();
        if (tabelSatuInterface.destroy(id)) {
            data.put("status", "ok");
        } else {
            data.put("status", "fail");
        }
        return (new Gson()).toJson(data);
    }
}
