
<#import "../parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/appointment" class="form-inline">
                <input type="date" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Введите дату">
                <button type="submit" class="btn btn-outline-primary ml-2">Поиск</button>
            </form>
        </div>
    </div>

    <#include "../appointment/appointmentNew.ftl" />

    <#include "../appointment/appointmentList.ftl" />

</@c.page>