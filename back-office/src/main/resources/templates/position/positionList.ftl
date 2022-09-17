<div class="card-columns">
    <#list positions?sort_by("name") as pos>
        <div class="card my-3">
            <div class="m-2">
                <a href="/position/${pos.id}"><span>${pos.name}</span></a><br/>
            </div>
        </div>
    <#else>
        Нет должностей
    </#list>
</div>