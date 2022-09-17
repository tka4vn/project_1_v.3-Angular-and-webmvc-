<#include "../parts/security.ftl">

<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th scope="col">№</th>
        <th scope="col">Пользователь</th>
        <th scope="col">Сервис</th>
        <th scope="col">Роль</th>
    </tr>
    </thead>
    <tbody>
    <#list ingress?sort_by("idIngress") as ing>
        <tr>
            <th scope="row">${ing.idIngress}</th>
            <td>${ing.idIngUse.name}</td>
            <td>${ing.idIngSer.name}</td>
            <td>${ing.idIngRol.name}
                <span class="mr-3" style="float: right;"><a href="/ingress/delete/${ing.idIngress}" style="color: #e11c1c">х</a></span>
            </td>
        </tr>
    </#list>
    </tbody>
</table>