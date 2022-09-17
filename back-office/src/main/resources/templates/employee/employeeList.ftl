<div class="card-columns">
    <#list employees?sort_by("fullName") as emp>
        <div class="card my-3">
            <div class="m-2">
                <a href="/employee/${emp.id}"><span>${emp.fullName}</span></a><br/>
            </div>
        </div>
    <#else>
        Нет сотрудников
    </#list>
</div>