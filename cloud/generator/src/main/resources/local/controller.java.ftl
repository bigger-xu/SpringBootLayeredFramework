package ${package.Controller};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Parent}.entity.${entity};
import ${package.Parent}.model.web.req.${entity}Req;
import ${package.Parent}.model.web.vo.${entity}VO;
import ${package.Parent}.service.I${entity}Service;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import com.efreight.common.global.PageParam;
import com.efreight.common.response.MessageInfo;
import com.efreight.common.utils.ObjectConvertUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>

/**
* <p>
 * ${table.comment!}
 * </p>
*
* @author ${author}
* @since ${date}
*/
@Tag(name = "${table.comment!}")
@Slf4j
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequiredArgsConstructor
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    private final I${entity}Service i${entity}Service;

    @PostMapping("/page")
    @Operation(summary = "分页")
    public MessageInfo<Page<${entity}VO>> page(@RequestBody PageParam pageReq) {
        Page<${entity}> page = i${entity}Service.page(new Page<>(pageReq.getCurrent(), pageReq.getSize()));
        Page<${entity}VO> pageVO = new Page<>();
        ObjectConvertUtils.pageCopyProperties(page, pageVO, ${entity}VO.class);
        return MessageInfo.ok(pageVO);
    }

    @PostMapping("/save")
    @Operation(summary = "新增")
    public void save(@RequestBody ${entity}Req req) {
        ${entity} entity = new ${entity}();
        ObjectConvertUtils.beanCopyProperties(req, entity);
        i${entity}Service.save(entity);
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    public void update(@RequestBody ${entity}Req req) {
        ${entity} entity = new ${entity}();
        ObjectConvertUtils.beanCopyProperties(req, entity);
        i${entity}Service.updateById(entity);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除")
    @Parameters({@Parameter(name = "id", description = "主键ID", required = true, in= ParameterIn.QUERY)})
    public void delete(Long id) {
        i${entity}Service.removeById(id);
    }

    @GetMapping("/detail")
    @Operation(summary = "详情")
    @Parameters({@Parameter(name = "id", description = "主键ID", required = true, in= ParameterIn.QUERY)})
    public MessageInfo<${entity}VO> detail(Long id) {
        ${entity} entity = i${entity}Service.getById(id);
        ${entity}VO vo = ObjectConvertUtils.beanCopyProperties(entity, ${entity}VO.class);
        return MessageInfo.ok(vo);
    }
}