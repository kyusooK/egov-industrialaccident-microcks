<template>

    <v-card outlined>
        <v-card-title>
            MyClaim # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="AccidentId" v-model="item.accidentId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="BusinessCode" v-model="item.businessCode" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="EmployeeId" v-model="item.employeeId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Name" v-model="item.name" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="HospitalCode" v-model="item.hospitalCode" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="Amount" v-model="item.amount" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Status" v-model="item.status" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Comments" v-model="item.comments" :editMode="editMode" @change="change" />
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
    name: 'MyClaimViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/myClaims/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

