import axios from "axios";
import Pagination from "Pagination";
import { useEffect, useState } from "react";
import { SalePage } from "types/sale";
import { formatLocalDate } from "utils/format";
import { BASE_URL } from "utils/requstes";

const DataTable = () => {
    const [activePage, setActivePage] = useState(0);
    const [page, setPage] = useState<SalePage>({
        last: true,
        number: 0,
        totalElements: 0,
        totalPages: 0,
        first: true,
    });

    useEffect(() => {
        axios.get(`${BASE_URL}/sales?page=${activePage}&size=30&sort=date,desc`)
            .then(Response => {
                setPage(Response.data);
            });
    }, [activePage]);
    const changePage = (index: number) => {
        setActivePage(index);
    }

    return (
        <>
            <Pagination page={page}  onPageChance={changePage} />
            <div className="table-responsive">
                <table className="table table-striped table-sm">
                    <thead>
                        <tr>
                            <th>Data</th>
                            <th>Vendedor</th>
                            <th>Clientes visitados</th>
                            <th>Negócios fechados</th>
                            <th>Valor</th>
                        </tr>
                    </thead>
                    <tbody>
                        {page.content?.map(item => (
                            <tr key={item.id}>
                                <td>{formatLocalDate(item.date, "dd/MM/yyyy")}</td>
                                <td>{item.seller.name}</td>
                                <td>{item.visited}</td>
                                <td>{item.deals}</td>
                                <td>{item.amount.toFixed(2)}</td>
                            </tr>
                        ))}

                    </tbody>
                </table>
            </div>
        </>
    );
}

export default DataTable;