```graphql
query All {
  customers {
    id
    age
    city
    name
  }
}

query ById {
  customerById(id: "2") {
    name
  }
}

mutation Create($customer: CustomerInput!) {
  createCustomer(customer:  $customer) {
    id,
    name,
    age,
    city
  }
}

mutation Update {
  updateCustomer(id: 1, customer:  {
    name: "jackson"
  }) {
    id,
    name,
    age,
    city
  }
}

mutation Delete {
  deleteCustomer(id: 1) {
    id,
    status
  }
}
```
variables
```json
{
  "customer": {
    "name": "obie",
    "age": 45,
    "city": "detroit"
  }
}
```