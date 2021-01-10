import { useEffect, useState } from 'react'
import { toast } from 'react-toastify';

import ProductsList from './ProductsList'
import StepsHeader from './StepsHeader'
import { OrderLocationData, Product } from './types';
import { fetchProducts, saveOrder } from '../api';
import OrderLocation from './OrderLocation';
import OrderSummary from './OrderSummary';
import Footer from '../Footer';
import { checkIsSelected } from './helpers';
import './styles.css'
import Loading from '../Loading/Loading';

function Orders() {
  //estado, sempre uma variavel e uma função, o useSatae inicia com uma lista vazia 
  const [products, setProducts] = useState<Product[]>([]);
  const [selectedProducts, setSelectedProducts] = useState<Product[]>([]);
  const [orderLocation, setOrderLocation] = useState<OrderLocationData>();
  const [done, setDone] = useState(false);
  const totalPrice = selectedProducts.reduce((sum, item) => {
    return sum + item.price
  }, 0);

  //carregar os dados do banco de dados
  useEffect(() => {
    setTimeout(() => {
      fetchProducts()
        .then(response => { setProducts(response.data); setDone(true); })
        .catch(error => { toast.warning("Error ao carregar os dados do servidor, favor atualize a pagina." + error); })
    }, 1200);
  }, [])

  //estado para contar todos os produtos selecionados 
  const handleSelectProduct = (product: Product) => {
    const isAlreadySelected = checkIsSelected(selectedProducts, product);

    if (isAlreadySelected) {
      const selected = selectedProducts.filter(item => item.id !== product.id);
      setSelectedProducts(selected);
    } else {
      setSelectedProducts(previous => [...previous, product]);
    }
  }

  //função para submeter pedido
  const handleSubmit = () => {
    const productsIds = selectedProducts.map(({ id }) => ({ id }));
    const payload = {
      ...orderLocation!,
      products: productsIds
    }
    
    if (payload.products.length > 0) {
      console.log("Enviado", payload)
      if (payload.address) {
        saveOrder(payload)
          .then((response) => {
            toast.error(`Pedido enviado com sucesso! Nº ${response.data.id}`);
            setSelectedProducts([]);
            setOrderLocation(undefined);
          })
          .catch(() => {
            toast.warning('Erro ao enviar pedido');
          })
      } else { toast.warning('Selecione um endereço'); }
    } else { toast.warning('Selecione um produto'); }
  }

  return (
    <>
      <div className="orders-container">
        <StepsHeader />
        <div>
          {!done ? (<Loading />) : (
            <>
              <ProductsList products={products} onSelectProduct={handleSelectProduct} selectedProducts={selectedProducts} />
              <OrderLocation onChangeLocation={location => setOrderLocation(location)} />
              <OrderSummary amount={selectedProducts.length} totalPrice={totalPrice} onSubmit={handleSubmit} />
            </>
          )}
        </div>
      </div>
      <Footer />
    </>
  )
}

export default Orders;