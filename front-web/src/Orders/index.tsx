import { useEffect, useState } from 'react'
import './styles.css'
import ProductsList from './ProductsList'
import StepsHeader from './StepsHeader'
import { OrderLocationData, Product } from './types';
import { fetchProducts } from '../api';
import OrderLocation from './OrderLocation';

function Orders(){
    //estado, sempre uma variavel e uma função, o useSatae inicia com uma lista vazia 
    const [products, setProducts] = useState<Product[]>([]);
    const [orderLocation, setOrderLocation] =  useState<OrderLocationData>();

    //carregar os dados do banco de dados
    useEffect(() => {
        fetchProducts()
            .then(response => setProducts(response.data))
            .catch(error => alert ("Error ao carregar os dados do servidor, favor atualize a pagina." + error))
    }, [])

    return(
        <div className="orders-container">
            <StepsHeader />
            <ProductsList products={products} />
            <OrderLocation onChangeLocation={location => setOrderLocation(location)}/>
        </div>
    )
}

export default Orders;