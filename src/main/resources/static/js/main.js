var crossApi = Vue.resource('/port{/id}');

Vue.component('port-row', {
    props: ['port'],
    template:
        '<div>' +
            '<i>{{ port.number }}</i>{{port.status.value}} {{port.service}} {{port.comment}}' +
        '</div>'
});

Vue.component('ports-list', {
    props: ['ports'],
    template:
        '<div>' +
            '<crosses-row v-for="port in ports" :key="cross.id" :port="port" />' +
        '</div>',
    created: function () {
        crossApi.get().then(result =>
            result.json().then(data =>
                data.forEach(port => this.ports.push(port))
            )
        )
    }
});

var app = new Vue({
    el: '#app',
    template: '<ports-list :ports="ports" />',
    data: {
        crosses: []
    }
});