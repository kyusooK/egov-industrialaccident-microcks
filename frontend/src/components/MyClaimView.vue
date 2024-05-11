<template>

    <v-data-table
        :headers="headers"
        :items="myClaim"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'MyClaimView',
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
                { text: "employeeId", value: "employeeId" },
                { text: "name", value: "name" },
                { text: "hospitalCode", value: "hospitalCode" },
                { text: "amount", value: "amount" },
                { text: "status", value: "status" },
                { text: "comments", value: "comments" },
                { text: "date", value: "date" },
            ],
            myClaim : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/myClaims'))

            temp.data._embedded.myClaims.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.myClaim = temp.data._embedded.myClaims;
        },
        methods: {
        }
    }
</script>

