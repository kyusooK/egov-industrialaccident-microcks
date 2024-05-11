<template>

    <v-data-table
        :headers="headers"
        :items="dataAnalysis"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'DataAnalysisView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "accidentId", value: "accidentId" },
                { text: "businessCode", value: "businessCode" },
                { text: "hospitalCode", value: "hospitalCode" },
                { text: "results", value: "results" },
                { text: "date", value: "date" },
            ],
            dataAnalysis : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/dataAnalyses'))

            temp.data._embedded.dataAnalyses.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.dataAnalysis = temp.data._embedded.dataAnalyses;
        },
        methods: {
        }
    }
</script>

