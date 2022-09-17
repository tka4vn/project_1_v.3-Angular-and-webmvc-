
<a class="btn btn-outline-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Новая заявка
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="date" class="form-control"
                       value="<#if requests??>${requests.dateReq}</#if>" name="dateReq" placeholder="Введите дату">
            </div>
            <div class="input-group mb-3">
                <select id="select-1" class="custom-select" name="idReqSer">
                    <option value="select">Выберите сервис ...</option>
                    <#list services?sort_by("name") as service>
                        <option value="${service.idServices}">${service.name}</option>
                    </#list>
                </select>

            </div>
            <div class="input-group mb-3">
                <select id="select-2" class="custom-select" name="idReqRol">
                    <option class="select" value="select">Выберите роль ...</option>
                    <#list services?sort_by("name") as service>
                        <option>
                        <#list service.idSerRol?sort_by("name") as idSerRol>
                            <option class="${service.idServices}" value="${idSerRol.idRole}">${idSerRol.name}</option>
                        </#list>
                        </option>
                    </#list>
                </select>
            </div>
            <input type="hidden" value="1" name="idReqSta">
<#--            <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
            <input type="hidden" name="id" value="<#if requests??>${requests.id}</#if>" />
            <div class="form-group mb-5">
                <button type="submit" class="btn btn-outline-success">Сохранить</button>
            </div>
        </form>
    </div>
</div>







