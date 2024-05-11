<template>

    <v-card outlined>
        <v-card-title>
            DataAnalysis # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="AccidentId" v-model="item.accidentId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="BusinessCode" v-model="item.businessCode" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="HospitalCode" v-model="item.hospitalCode" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Results" v-model="item.results" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="Date" v-model="item.date" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'DataAnalysisViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/dataAnalyses/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

