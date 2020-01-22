package cindy.duatabel.controller;

import cindy.duatabel.model.TabelDua;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.reactivex.annotations.Nullable;

import com.google.gson.Gson;
import cindy.duatabel.repository.*;

import cindy.duatabel.intfc.TabelDuaInterface;

import java.util.HashMap;
import java.util.List;

@Validated
@Controller("/tabeldua")
public class TabelDuaController {

    private TabelDuaInterface tabelDuaInterface;

    public TabelDuaController(TabelDuaInterface tabelDuaInterface) {
        this.tabelDuaInterface = tabelDuaInterface;
    }

    @Get(processes = MediaType.APPLICATION_JSON)
    public String index(@Nullable @QueryValue final int page, @QueryValue final int limit) {
        final HashMap<String, Object> data = new HashMap<>();
        try {
            final List<TabelDua> tabeldua = tabelDuaInterface.findAll(page, limit);
            data.put("status", "ok");
            data.put("message", "Data Project");
            data.put("page", Math.ceil(tabelDuaInterface.size() / limit));
            data.put("data", tabeldua);
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
        return (new Gson().toJson(tabelDuaInterface.findById(id)));
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String save(@Body final TabelDua tabeldua) {
        final HashMap<String, Object> data = new HashMap<>();
        try {
            // boolean exist = repository.existByName(projecttype.getName());
            if (tabeldua.getName() == null || tabeldua.getName() == "") {
                return "nama tidak boleh kosong";
            }
            if (tabeldua.getCategory() == null || tabeldua.getCategory() == "") {
                return "category tidak boleh kosong";
            }
            
            // if(exist == true) {
            TabelDua tbdua = tabelDuaInterface.save(tabeldua);
            if (tbdua != null) {
                data.put("status", "ok");
                data.put("message", "Data Project");
                data.put("data", tbdua);
                return (new Gson().toJson(data));
            } else {
                data.put("status", "error");
                data.put("message", "failed data already exist");
                data.put("data", tbdua);
                return (new Gson().toJson(data));
            }
            // }
            // return (new Gson()).toJson(data);
        } catch (Exception e) {
            String message = e.getMessage();
            data.put("status", "errors");
            data.put("message", message);
            return (new Gson().toJson(data));
        }
    }

    @Put("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@PathVariable @Nullable final Long id, @Body final TabelDua tabeldua) {
        final HashMap<String, Object> data = new HashMap<>();
        try {
            if (tabeldua.getName() == null || tabeldua.getName() == "") {
                return "nama tidak boleh kosong";
            }
            if (tabeldua.getCategory() == null || tabeldua.getCategory() == "") {
                return "category tidak boleh kosong";
            }
            
            // if(exist == true){
            // data.put("status", "error");
            // data.put("message", "nama tidak boleh sama");
            // return (new Gson().toJson(data));
            // } else {
            TabelDua tbdua = tabelDuaInterface.update(id, tabeldua);
            if (tbdua != null) {
                data.put("status", "ok");
                data.put("message", "berhasil memperbarui data");
                data.put("data", tbdua);

                return (new Gson().toJson(data));
            } else {
                data.put("status", "fail");
                data.put("message", "data not found");

                return (new Gson().toJson(data));
            }
            // }
        } catch (Exception e) {
            String message = e.getMessage();
            data.put("status", "error");
            data.put("message", message);
            return (new Gson().toJson(data));
        }
    }

    @Delete("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String destroy(@PathVariable @Nullable final Long id) {
        final HashMap<String, Object> data = new HashMap<>();
        TabelDua tbdua = tabelDuaInterface.destroy(id);
        if (tbdua != null) {
            data.put("status", "ok");
            data.put("message", "berhasil menghapus data");
            data.put("data", tbdua);
        } else {
            data.put("status", "fail");
        }
        return (new Gson().toJson(data));
    }
}