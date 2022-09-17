
<a class="btn btn-outline-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Создать роль
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if role??>${role.name}</#if>" name="name" placeholder="Введите имя">
                <#if nameError??>
                    <div class="invalid-feedback">
                        ${nameError}
                    </div>
                </#if>
            </div>
<#--            <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
            <input type="hidden" name="id" value="<#if service??>${role.name}</#if>" />
            <div class="form-group mb-5">
                <button type="submit" class="btn btn-outline-success">Сохранить</button>
            </div>
        </form>
    </div>
</div>